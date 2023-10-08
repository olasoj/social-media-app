package com.olasoj.socialapp.follow.repository;

import com.olasoj.socialapp.follow.FollowRelationShip;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FollowRepository {
    boolean saveFollowRelation(Long ownerAccount, Long followAccount);

    boolean updateFollowRelation(FollowRelationShip followRelationShip);

    Optional<FollowRelationShip> findFollowRelationShip(Long ownerAccount, Long followAccount);

    List<Map<String, Object>> findAllFollowingRelationShip(Long socialAccountId);

    List<Map<String, Object>> findAllFollowersRelationShip(Long socialAccountId);
}
