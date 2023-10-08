package com.olasoj.socialapp.registration;

import com.olasoj.socialapp.user.model.CreateUserRequest;
import com.olasoj.socialapp.util.response.model.Response;
import com.olasoj.socialapp.util.response.transformer.ResponseAssembler;
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
public class RegistrationInboundRestController {

    private final RegistrationService registrationService;

    public RegistrationInboundRestController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<Response<RegistrationResult>> createBlogPost(@Valid @RequestBody RegistrationRequest registrationRequest) throws URISyntaxException {
        RegistrationResult registrationResult = registrationService.registerUser(registrationRequest);
        Response<RegistrationResult> model = ResponseAssembler.toResponse(HttpStatus.CREATED, registrationResult);
        return ResponseEntity.created(new URI("")).body(model);
    }

}
