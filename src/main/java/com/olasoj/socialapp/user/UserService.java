package com.olasoj.socialapp.user;

import com.olasoj.socialapp.user.model.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    CreateUserResult createUser(CreateUserRequest createUserRequest);

    UserAndAccountInfo getUserWithAccountInfo(String username);
    UserWithPageInfoResult getAllUserWithAccountInfo(ReadUsersRequest readUsersRequest);

    User findUserByUsername(String username);
}
