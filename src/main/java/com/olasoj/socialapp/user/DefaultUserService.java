package com.olasoj.socialapp.user;

import com.olasoj.socialapp.registration.RegistrationResult;
import com.olasoj.socialapp.user.acl.role.Role;
import com.olasoj.socialapp.user.model.*;
import com.olasoj.socialapp.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder argon2PasswordEncoder;

    public DefaultUserService(@Qualifier("bCryptPasswordEncoder") PasswordEncoder argon2PasswordEncoder, @Qualifier("JDBCUserRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
        this.argon2PasswordEncoder = argon2PasswordEncoder;
    }

    @Override
    @Transactional
    public CreateUserResult createUser(CreateUserRequest createUserRequest) {

        validateExistingUser(createUserRequest);
        String encodePassword = argon2PasswordEncoder.encode(createUserRequest.getPassword());

        User user = BlogUser.builder()
                .password(encodePassword)
                .username(createUserRequest.getUsername())
                .email(createUserRequest.getEmail())
                .accessControlList(List.of(Role.WRITE, Role.READ))
                .profilePhoto("")
                .build();

        boolean saveUser = userRepository.saveUser(user);
        return new CreateUserResult(user);
    }

    private void validateExistingUser(CreateUserRequest createUserRequest) {
        userRepository
                .findUser(createUserRequest.getUsername())
                .ifPresent(u -> {
                    throw new ResponseStatusException(HttpStatusCode.valueOf(409), "UserName taken");
                });
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository
                .findUser(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatusCode.valueOf(404), "No User Found"));

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
                .findUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("No User Found"));

        return new BlogUserPrincipal(user);
    }
}
