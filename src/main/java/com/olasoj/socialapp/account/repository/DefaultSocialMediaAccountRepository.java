package com.olasoj.socialapp.account.repository;

import com.olasoj.socialapp.account.model.SocialMediaAccount;
import com.olasoj.socialapp.audit.AuditUtils;
import com.olasoj.socialapp.util.db.DBTimeUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.olasoj.socialapp.account.repository.SocialMediaAccountSQLStatements.fetchSocialMediaAccountByUserId;
import static com.olasoj.socialapp.account.repository.SocialMediaAccountSQLStatements.insertNewUser;

@Repository
public class DefaultSocialMediaAccountRepository implements SocialMediaAccountRepository {

    private final JdbcTemplate jdbcOperations;

    public DefaultSocialMediaAccountRepository(@Qualifier("jdbcTemplate") JdbcTemplate jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public boolean saveSocialMediaAccount(SocialMediaAccount socialMediaAccount) {

        AuditUtils.onCreate(socialMediaAccount);

        int update = jdbcOperations.update(
                insertNewUser,
                socialMediaAccount.getCreatedBy(),
                socialMediaAccount.getUpdatedBy(),
                socialMediaAccount.getUserId()
        );

        return update == 1;
    }

    @Override
    public Optional<SocialMediaAccount> findSocialMediaAccountByUserId(Long userId) {

        return Optional.ofNullable(
                jdbcOperations.query(
                        fetchSocialMediaAccountByUserId
                        , rs -> {
                            return SocialMediaAccountRowMapper.socialMediaAccountRowMapper.mapRow(rs, rs.getRow());
                        }
                        , userId
                )
        );
    }

    @Override
    public Optional<SocialMediaAccount> findSocialMediaAccountById(Long socialMediaAccountId) {

        return Optional.ofNullable(
                jdbcOperations.query(
                        """
                                SELECT social_media_account_id FROM social_media_account WHERE social_media_account_id = ?  
                                """
                        , rs -> {
                            return SocialMediaAccountRowMapper.socialMediaAccountRowMapper.mapRow(rs, rs.getRow());
                        }
                        , socialMediaAccountId
                )
        );
    }

    static final class SocialMediaAccountRowMapper implements RowMapper<SocialMediaAccount> {

        static final SocialMediaAccountRowMapper socialMediaAccountRowMapper = new SocialMediaAccountRowMapper();


        public SocialMediaAccount mapRow(ResultSet rs, int rowNum) throws SQLException {

            if (rs.next()) {

                SocialMediaAccount socialMediaAccount = new SocialMediaAccount();

                socialMediaAccount.setSocialMediaAccountId(rs.getLong("social_media_account_id"));
                socialMediaAccount.setUserId(rs.getLong("user_id"));
                socialMediaAccount.setCreatedAt(DBTimeUtils.getInstant(rs, "created_at"));
                socialMediaAccount.setUpdatedAt(DBTimeUtils.getInstant(rs, ("updated_at")));
                socialMediaAccount.setCreatedBy(rs.getString("created_by"));
                socialMediaAccount.setUpdatedBy(rs.getString("updated_by"));
                socialMediaAccount.setVersion(rs.getInt("version"));
                return socialMediaAccount;
            }

            return null;
        }
    }
}
