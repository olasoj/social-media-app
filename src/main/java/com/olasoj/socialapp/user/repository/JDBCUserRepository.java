package com.olasoj.socialapp.user.repository;

import com.olasoj.socialapp.audit.AuditUtils;
import com.olasoj.socialapp.user.acl.role.Role;
import com.olasoj.socialapp.user.model.BlogUser;
import com.olasoj.socialapp.user.model.User;
import com.olasoj.socialapp.user.model.UserAndAccountInfo;
import com.olasoj.socialapp.util.db.DBTimeUtils;
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
                return BlogUser.builder()
                        .userId(rs.getLong("user_id"))
                        .username(rs.getString("username"))
                        .email(rs.getString("email"))
                        .password(rs.getString("password"))

                        .createdAt(DBTimeUtils.getInstant(rs, "created_at"))
                        .updatedAt(DBTimeUtils.getInstant(rs, ("updated_at")))
                        .createdBy(rs.getString("created_by"))
                        .updatedBy(rs.getString("updated_by"))

                        .profilePhoto(rs.getString("profile_picture"))
                        .version(rs.getInt("version"))

                        .accessControlList(List.of(Role.WRITE, Role.READ))
                        .build();
            }

            return null;
        }
    }

    static final class UserAndAccountInfoRowMapper implements RowMapper<UserAndAccountInfo> {

        static final RowMapper<UserAndAccountInfo> userAndAccountInfoRowMapper = new UserAndAccountInfoRowMapper();


        public UserAndAccountInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

            if (rs.next()) {
                User user = userRowMapper.mapRow(rs, rowNum);
                return new UserAndAccountInfo(user, rs.getLong("social_media_account_id"));
            }

            return null;
        }
    }
}
