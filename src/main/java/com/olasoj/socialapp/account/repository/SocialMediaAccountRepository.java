package com.olasoj.socialapp.account.repository;

import com.olasoj.socialapp.account.model.SocialMediaAccount;

import java.util.Optional;

public interface SocialMediaAccountRepository {

    boolean saveSocialMediaAccount(SocialMediaAccount socialMediaAccount);

    Optional<SocialMediaAccount> findSocialMediaAccountByUserId(Long userId);


}
