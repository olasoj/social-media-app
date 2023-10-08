package com.olasoj.socialapp.user;

import com.olasoj.socialapp.user.model.CreateUserRequest;
import com.olasoj.socialapp.registration.RegistrationResult;
import com.olasoj.socialapp.user.model.CreateUserResult;
import com.olasoj.socialapp.user.model.User;
import com.olasoj.socialapp.user.model.UserAndAccountInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    CreateUserResult createUser(CreateUserRequest createUserRequest);

    UserAndAccountInfo getUserWithAccountInfo(String username);

    User findUserByUsername(String username);
}
