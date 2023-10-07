package com.primebank.blog.auth;

import com.primebank.blog.auth.model.request.AuthenticateViaAccessJWTRequest;
import com.primebank.blog.auth.model.request.AuthenticateViaUsernameAndPasswordRequest;
import com.primebank.blog.auth.model.response.AuthenticateViaAccessJWTResult;
import com.primebank.blog.auth.model.response.AuthenticateViaUsernameAndPasswordResult;

public interface AuthService {

    AuthenticateViaAccessJWTResult authenticateViaAccessJWT(AuthenticateViaAccessJWTRequest authenticateViaAccessJWTRequest);

    AuthenticateViaUsernameAndPasswordResult authenticateViaUsernameAndPassword(AuthenticateViaUsernameAndPasswordRequest authRestInboundRequest);
}
