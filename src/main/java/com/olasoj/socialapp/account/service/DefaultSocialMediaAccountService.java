package com.olasoj.socialapp.account.service;

import com.olasoj.socialapp.account.model.CreateNewSocialAccountRequest;
import com.olasoj.socialapp.account.model.SocialMediaAccount;
import com.olasoj.socialapp.account.repository.SocialMediaAccountRepository;
import com.olasoj.socialapp.user.UserService;
import com.olasoj.socialapp.user.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DefaultSocialMediaAccountService implements SocialMediaAccountService {

    private final SocialMediaAccountRepository socialMediaAccountRepository;
    private final UserService userService;

    public DefaultSocialMediaAccountService(@Qualifier("defaultSocialMediaAccountRepository") SocialMediaAccountRepository socialMediaAccountRepository, @Qualifier("defaultUserService") UserService userService) {
        this.socialMediaAccountRepository = socialMediaAccountRepository;
        this.userService = userService;
    }

    @Override
    @Transactional
    public boolean createSocialMediaAccount(CreateNewSocialAccountRequest createNewSocialAccountRequest) {

        User user = userService.findUserByUsername(createNewSocialAccountRequest.getUsername());
        validateExistingSocialMediaAccount(user);
        return socialMediaAccountRepository.saveSocialMediaAccount(new SocialMediaAccount(user.getUserId()));
    }

    @Override
    @Transactional
    public void validateExistingSocialMediaAccount(Long socialAccountId) {

        socialMediaAccountRepository
                .findSocialMediaAccountById(socialAccountId)
                .orElseThrow(() -> {
                    throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Account not found");
                });
    }

    private void validateExistingSocialMediaAccount(User user) {
        socialMediaAccountRepository
                .findSocialMediaAccountByUserId(user.getUserId())
                .ifPresent(u -> {
                    throw new ResponseStatusException(HttpStatusCode.valueOf(404), "Account not found");
                });
    }

}

