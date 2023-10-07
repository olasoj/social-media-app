package com.primebank.blog.user.repository;

import com.primebank.blog.user.model.User;

import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);

    Optional<User> findUser(String userId);
}
