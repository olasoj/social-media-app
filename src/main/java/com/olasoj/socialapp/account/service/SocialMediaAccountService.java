package com.olasoj.socialapp.account.service;

import com.olasoj.socialapp.account.model.CreateNewSocialAccountRequest;
import org.springframework.transaction.annotation.Transactional;

public interface SocialMediaAccountService {

    //Insert a new social media account
    boolean createSocialMediaAccount(CreateNewSocialAccountRequest createNewSocialAccountRequest);

    @Transactional
    void validateExistingSocialMediaAccount(Long socialAccountId);
}
