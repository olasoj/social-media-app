package com.olasoj.socialapp.follow.repository;

import com.olasoj.socialapp.audit.AuditUtils;
import com.olasoj.socialapp.follow.FollowRelationShip;
import com.olasoj.socialapp.util.db.DBTimeUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static com.olasoj.socialapp.follow.repository.FollowSQLStatements.*;

@Repository
public class DefaultFollowRelationShipRepository implements FollowRepository {

    private final JdbcTemplate jdbcOperations;

    public DefaultFollowRelationShipRepository(@Qualifier("jdbcTemplate") JdbcTemplate jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public boolean saveFollowRelation(Long ownerAccount, Long followAccount) {

        int update = jdbcOperations.update(
                insertNewPost,
                AuditUtils.getUsername(),
                AuditUtils.getUsername(),
                followAccount,
                ownerAccount
        );

        return update == 1;
    }

    @Override
    public boolean updateFollowRelation(FollowRelationShip followRelationShip) {

        int update = jdbcOperations.update(
                updateFollowRelationshipByFollowId,
                followRelationShip.getFollowStatus().name(),
                AuditUtils.getUsername(),
                followRelationShip.getFollowId()
        );

        return update == 1;
    }

    @Override
    public Optional<FollowRelationShip> findFollowRelationShip(Long ownerAccount, Long followAccount) {

        return Optional.ofNullable(
                jdbcOperations.query(
                        fetchUniqueFollowRelationshipByFollowSID
                        , rs -> {
                            return FollowRelationShipRowMapper.FOLLOW_RELATION_SHIP_ROW_MAPPER.mapRow(rs, rs.getRow());
                        }
                        , followAccount
                        , ownerAccount
                )
        );
    }

    @Override
    public List<Map<String, Object>> findAllFollowingRelationShip(Long socialAccountId) {
        return jdbcOperations.queryForList(
                fetchFollowRelationshipByFollowingSID
                , socialAccountId
        );
    }

    @Override
    public List<Map<String, Object>> findAllFollowersRelationShip(Long socialAccountId) {
        return jdbcOperations.queryForList(
                fetchFollowRelationshipByFollowSID
                , socialAccountId
        );
    }


    static final class FollowRelationShipRowMapper implements RowMapper<FollowRelationShip> {

        static final FollowRelationShipRowMapper FOLLOW_RELATION_SHIP_ROW_MAPPER = new FollowRelationShipRowMapper();


        public FollowRelationShip mapRow(ResultSet rs, int rowNum) throws SQLException {

            if (rs.next()) {

                FollowRelationShip followRelationShip = new FollowRelationShip();

                followRelationShip.setSocialMediaAccountId(rs.getLong("social_media_account_id"));
                followRelationShip.setFollowSocialMediaAccountId(rs.getLong("follow_social_media_account_id"));
                followRelationShip.setFollowId(rs.getLong("follow_id"));
                followRelationShip.setFollowStatus(Objects.isNull(rs.getString("follow_id")) ? null : FollowStatus.valueOf(rs.getString("follow_id")));

                followRelationShip.setCreatedAt(DBTimeUtils.getInstant(rs, "created_at"));
                followRelationShip.setUpdatedAt(DBTimeUtils.getInstant(rs, ("updated_at")));
                followRelationShip.setCreatedBy(rs.getString("created_by"));
                followRelationShip.setUpdatedBy(rs.getString("updated_by"));

                followRelationShip.setVersion(rs.getInt("version"));
                return followRelationShip;
            }

            return null;
        }
    }
}
