package com.primebank.blog.user;

import com.primebank.blog.user.model.CreateUserRequest;
import com.primebank.blog.user.model.CreateUserResult;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    CreateUserResult createUser(CreateUserRequest createUserRequest);
}
