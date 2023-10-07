package com.olasoj.socialapp.auth.jwt.provider;

import com.olasoj.socialapp.auth.jwt.model.CreateJwTokenRequest;
import com.olasoj.socialapp.auth.jwt.model.CreateJwTokenResult;
import com.olasoj.socialapp.auth.jwt.model.JwToken;
import com.olasoj.socialapp.auth.jwt.model.JwTokenDecipherRequest;

public interface JwtTokenProvider {

    String ISSUER = "Blog App";

    JwToken readToken(JwTokenDecipherRequest jwTokenDecipherRequest);

    CreateJwTokenResult createToken(CreateJwTokenRequest jwToken);
}
