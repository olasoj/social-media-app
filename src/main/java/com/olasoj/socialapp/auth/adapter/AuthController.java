package com.olasoj.socialapp.auth.adapter;

import com.olasoj.socialapp.auth.model.request.AuthRestInboundRequest;
import com.olasoj.socialapp.util.response.model.Response;
import com.olasoj.socialapp.util.response.transformer.ResponseAssembler;
import com.olasoj.socialapp.auth.AuthService;
import com.olasoj.socialapp.auth.model.request.AuthenticateViaUsernameAndPasswordRequest;
import com.olasoj.socialapp.auth.model.response.AuthRestInboundResponse;
import com.olasoj.socialapp.auth.model.response.AuthenticateViaUsernameAndPasswordResult;
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