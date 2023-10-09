package com.olasoj.socialapp.follow.service;

import com.olasoj.socialapp.account.service.SocialMediaAccountService;
import com.olasoj.socialapp.follow.FollowRelationShip;
import com.olasoj.socialapp.follow.repository.FollowRepository;
import com.olasoj.socialapp.follow.repository.FollowStatus;
import com.olasoj.socialapp.user.model.BlogUserPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class DefaultFollowRelationshipService implements FollowRelationshipService {

    private final SocialMediaAccountService socialMediaAccountService;
    private final FollowRepository followRepository;

    public DefaultFollowRelationshipService(SocialMediaAccountService socialMediaAccountService, FollowRepository followRepository) {
        this.socialMediaAccountService = socialMediaAccountService;
        this.followRepository = followRepository;
    }

    @Override
    @Transactional
    public boolean toggleFollowStatus(Long socialAccountId, BlogUserPrincipal blogUserPrincipal) {

        //Validate the socialAccountId
        socialMediaAccountService.validateExistingSocialMediaAccount(socialAccountId);

        //Check if the relationship exist
        Optional<FollowRelationShip> followRelationShip = followRepository.findFollowRelationShip(blogUserPrincipal.accountId(), socialAccountId);

        AtomicBoolean result = new AtomicBoolean(false);

        if (followRelationShip.isEmpty()) {

            //Create
            return followRepository.saveFollowRelation(blogUserPrincipal.accountId(), socialAccountId);
        } else {

            //Update
            followRelationShip.ifPresent(followRelationShip1 -> {
                followRelationShip1.setFollowStatus((FollowStatus.ACCEPTED.equals(followRelationShip1.getFollowStatus()) ? FollowStatus.CANCEL : FollowStatus.ACCEPTED));
                result.set(followRepository.updateFollowRelation(followRelationShip1));
            });

            return result.get();
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getAllFollowers(Long socialAccountId) {
        return followRepository.findAllFollowersRelationShip(socialAccountId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, Object>> getAllFollowing(Long socialAccountId) {
        return followRepository.findAllFollowingRelationShip(socialAccountId);
    }
}
