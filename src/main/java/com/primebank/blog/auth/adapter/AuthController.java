package com.primebank.blog.auth.adapter;

import com.primebank.blog.auth.AuthService;
import com.primebank.blog.auth.model.request.AuthRestInboundRequest;
import com.primebank.blog.auth.model.request.AuthenticateViaUsernameAndPasswordRequest;
import com.primebank.blog.auth.model.response.AuthRestInboundResponse;
import com.primebank.blog.auth.model.response.AuthenticateViaUsernameAndPasswordResult;
import com.primebank.blog.util.response.model.Response;
import com.primebank.blog.util.response.transformer.ResponseAssembler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth", consumes = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {
    private final AuthService authService;

    public AuthController(@Qualifier("defaultAuthService") AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Response<AuthRestInboundResponse>> login(@Valid @RequestBody AuthRestInboundRequest authRestInboundRequest) {

        AuthenticateViaUsernameAndPasswordRequest authenticateViaUsernameAndPasswordRequest = new AuthenticateViaUsernameAndPasswordRequest(authRestInboundRequest.username(), authRestInboundRequest.password());
        AuthenticateViaUsernameAndPasswordResult authenticateViaUsernameAndPasswordResult = authService.authenticateViaUsernameAndPassword(authenticateViaUsernameAndPasswordRequest);

        AuthRestInboundResponse authRestInboundResponse = new AuthRestInboundResponse(authenticateViaUsernameAndPasswordResult.sessionId());
        Response<AuthRestInboundResponse> authResponse = ResponseAssembler.toResponse(HttpStatus.OK, authRestInboundResponse);

        return ResponseEntity.ok().body(authResponse);
    }
}