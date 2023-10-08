package com.olasoj.socialapp.account.service;

import com.olasoj.socialapp.account.model.CreateNewSocialAccountRequest;

public interface SocialMediaAccountService {

    //Insert a new social media account
    boolean createSocialMediaAccount(CreateNewSocialAccountRequest createNewSocialAccountRequest);
}
