package com.olasoj.socialapp.user.repository;

import com.olasoj.socialapp.user.model.User;

import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);

    Optional<User> findUser(String userId);
}
