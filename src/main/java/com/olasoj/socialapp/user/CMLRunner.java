package com.olasoj.socialapp.user;

import com.olasoj.socialapp.registration.RegistrationRequest;
import com.olasoj.socialapp.registration.RegistrationService;
import com.olasoj.socialapp.user.model.CreateUserRequest;
import com.olasoj.socialapp.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CMLRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CMLRunner.class);
    private final String email = "olasoji@eail.com";
    @Autowired
    RegistrationService registrationService;

    @Autowired
    @Qualifier("JDBCUserRepository")
    UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) {


//        CreateUserRequest createUserRequest = new CreateUserRequest();
//        createUserRequest.setEmail(email);
//        createUserRequest.setPassword(email);
//        createUserRequest.setUsername(email);
//        registrationService.registerUser(new RegistrationRequest(createUserRequest));
//
//        userRepository.findUser(email);
//        throw new RuntimeException();

    }
}
