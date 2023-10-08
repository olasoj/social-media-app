package com.olasoj.socialapp.user;

import com.olasoj.socialapp.user.model.CreateUserRequest;
import com.olasoj.socialapp.user.model.CreateUserResult;
import com.olasoj.socialapp.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    CreateUserResult createUser(CreateUserRequest createUserRequest);

    User findUserByUsername(String username);
}
