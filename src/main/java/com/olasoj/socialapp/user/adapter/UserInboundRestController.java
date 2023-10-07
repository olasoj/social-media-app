package com.olasoj.socialapp.user.adapter;

import com.olasoj.socialapp.user.model.CreateUserRequest;
import com.olasoj.socialapp.user.model.CreateUserResult;
import com.olasoj.socialapp.util.response.model.Response;
import com.olasoj.socialapp.util.response.transformer.ResponseAssembler;
import com.olasoj.socialapp.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE )
public class UserInboundRestController {

    private final UserService userService;

    public UserInboundRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<Response<CreateUserResult>> createBlogPost(@Valid @RequestBody CreateUserRequest createBlogPostRequest) throws URISyntaxException {
        CreateUserResult createUserResult = userService.createUser(createBlogPostRequest);
        Response<CreateUserResult> model = ResponseAssembler.toResponse(HttpStatus.CREATED, createUserResult);
        return ResponseEntity.created(new URI("")).body(model);
    }

}
