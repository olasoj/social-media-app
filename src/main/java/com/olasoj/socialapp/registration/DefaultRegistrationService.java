package com.olasoj.socialapp.registration;

import com.olasoj.socialapp.account.model.CreateNewSocialAccountRequest;
import com.olasoj.socialapp.account.service.SocialMediaAccountService;
import com.olasoj.socialapp.user.UserService;
import com.olasoj.socialapp.user.model.CreateUserRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultRegistrationService implements RegistrationService {

    private final UserService userService;
    private final SocialMediaAccountService socialMediaAccountService;

    public DefaultRegistrationService(@Qualifier("defaultUserService") UserService userService, @Qualifier("defaultSocialMediaAccountService") SocialMediaAccountService socialMediaAccountService) {
        this.userService = userService;
        this.socialMediaAccountService = socialMediaAccountService;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registerUser(RegistrationRequest registrationRequest) {
        CreateUserRequest createUserRequest = registrationRequest.getCreateUserRequest();
        userService.createUser(createUserRequest);
        socialMediaAccountService.createSocialMediaAccount(new CreateNewSocialAccountRequest(createUserRequest.getUsername()));
    }
}
