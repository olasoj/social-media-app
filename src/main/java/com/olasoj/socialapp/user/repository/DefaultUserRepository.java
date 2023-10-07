package com.olasoj.socialapp.user.repository;

import com.olasoj.socialapp.user.model.User;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class DefaultUserRepository implements UserRepository {
    public static final UserRepository userRepository = new DefaultUserRepository();
    private final ConcurrentMap<String, User> users;

    private DefaultUserRepository() {
        users = new ConcurrentHashMap<>();
    }

    @Override
    public User saveUser(User user) {
        Assert.notNull(user, "User cannot be null");

        synchronized (this.users) {
            return users.putIfAbsent(user.getUsername(), user);
        }
    }

    @Override
    public Optional<User> findUser(String userId) {
        Assert.notNull(userId, "Username cannot be null");

        return Optional.ofNullable(users.get(userId));
    }
}
