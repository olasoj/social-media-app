package com.olasoj.socialapp.user.repository;

import com.olasoj.socialapp.user.model.ReadUsersRequest;
import com.olasoj.socialapp.user.model.User;
import com.olasoj.socialapp.user.model.UserAndAccountInfo;
import com.olasoj.socialapp.user.model.UserWithPageInfoResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository {
    boolean saveUser(User user);

    Optional<User> findUser(String userId);

    @Transactional(readOnly = true)
    UserWithPageInfoResult findAllUsers(ReadUsersRequest readUsersRequest);

    @Transactional(readOnly = true)
    Optional<UserAndAccountInfo> findUserWithAccountId(String userId);
}
