package com.olasoj.socialapp.follow.service;

import com.olasoj.socialapp.follow.FollowRelationShip;

import java.util.List;
import java.util.Map;

public interface FollowRelationshipService {

    boolean toggleFollowStatus(Long socialAccountId);

    List<Map<String, Object>> getAllFollowers(Long socialAccountId);

    List<Map<String, Object>> getAllFollowing(Long socialAccountId);

}
