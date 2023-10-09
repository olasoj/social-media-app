package com.olasoj.socialapp.user.repository;

import com.olasoj.socialapp.audit.AuditUtils;
import com.olasoj.socialapp.post.model.PagingInfo;
import com.olasoj.socialapp.user.model.ReadUsersRequest;
import com.olasoj.socialapp.user.model.User;
import com.olasoj.socialapp.user.model.UserAndAccountInfo;
import com.olasoj.socialapp.user.model.UserWithPageInfoResult;
import com.olasoj.socialapp.user.transformer.UserAssembler;
import io.jsonwebtoken.lang.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.olasoj.socialapp.user.repository.JDBCUserRepository.UserAndAccountInfoRowMapper.userAndAccountInfoRowMapper;
import static com.olasoj.socialapp.user.repository.JDBCUserRepository.UserRowMapper.userRowMapper;
import static com.olasoj.socialapp.user.repository.UserSQLStatements.*;

@Component
public class JDBCUserRepository implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(JDBCUserRepository.class);

    private final JdbcTemplate jdbcOperations;

    public JDBCUserRepository(@Qualifier("jdbcTemplate") JdbcTemplate jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }


    @Override
    public boolean saveUser(User user) {

        AuditUtils.onCreate(user);

        int update = jdbcOperations.update(
                insertNewUser,
                user.getCreatedBy(),
                user.getUpdatedBy(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getProfilePhoto()
        );

        LOGGER.info("{} row(s) updated", update);

        return update == 1;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUser(String userId) {

        Optional<User> query = Optional.ofNullable(
                jdbcOperations.query(
                        fetchNewUserByUsername
                        , rs -> {
                            return userRowMapper.mapRow(rs, rs.getRow());
                        }
                        , userId
                )
        );

        query.ifPresent(user -> LOGGER.info("{}: user ", user));
        return query;
    }

    @Override
    @Transactional(readOnly = true)
    public UserWithPageInfoResult findAllUsers(ReadUsersRequest readUsersRequest) {

        Assert.notNull(readUsersRequest, "ReadUsersRequest cannot be null");
        PagingInfo pagingInfo = readUsersRequest.getPagingInfo();
        Assert.notNull(pagingInfo, "Paging cannot be null");

        List<Map<String, Object>> query = jdbcOperations.queryForList(
                fetchAllUsersAndAccountId
                , pagingInfo.getPageSize() * (pagingInfo.getCurrentPage() - 1)
                , pagingInfo.getPageSize()
        );

        return UserAssembler.assemble(query, pagingInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserAndAccountInfo> findUserWithAccountId(String userId) {

        Optional<UserAndAccountInfo> query = Optional.ofNullable(
                jdbcOperations.query(
                        fetchUserAndAccountIdByUsername
                        , rs -> {
                            return userAndAccountInfoRowMapper.mapRow(rs, rs.getRow());
                        }
                        , userId
                )
        );

        query.ifPresent(user -> LOGGER.info("{}: user ", user));
        return query;
    }

    static final class UserRowMapper implements RowMapper<User> {

        static final UserRowMapper userRowMapper = new UserRowMapper();

        public User mapRow(ResultSet rs, int rowNum) throws SQLException {

            if (rs.next()) {
                return UserAssembler.assemble(rs);
            }

            return null;
        }
    }

    static final class UserAndAccountInfoRowMapper implements RowMapper<UserAndAccountInfo> {

        static final RowMapper<UserAndAccountInfo> userAndAccountInfoRowMapper = new UserAndAccountInfoRowMapper();


        public UserAndAccountInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

            if (rs.next()) {
                User user = UserAssembler.assemble(rs);
                return new UserAndAccountInfo(user, rs.getLong("social_media_account_id"));
            }

            return null;
        }
    }
}
