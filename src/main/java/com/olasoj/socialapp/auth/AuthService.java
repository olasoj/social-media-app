package com.olasoj.socialapp.auth;

import com.olasoj.socialapp.auth.model.request.AuthenticateViaAccessJWTRequest;
import com.olasoj.socialapp.auth.model.request.AuthenticateViaUsernameAndPasswordRequest;
import com.olasoj.socialapp.auth.model.response.AuthenticateViaAccessJWTResult;
import com.olasoj.socialapp.auth.model.response.AuthenticateViaUsernameAndPasswordResult;

public interface AuthService {

    AuthenticateViaAccessJWTResult authenticateViaAccessJWT(AuthenticateViaAccessJWTRequest authenticateViaAccessJWTRequest);

    AuthenticateViaUsernameAndPasswordResult authenticateViaUsernameAndPassword(AuthenticateViaUsernameAndPasswordRequest authRestInboundRequest);
}
