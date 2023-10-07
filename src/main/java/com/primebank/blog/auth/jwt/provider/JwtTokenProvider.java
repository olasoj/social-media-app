package com.primebank.blog.auth.jwt.provider;

import com.primebank.blog.auth.jwt.model.CreateJwTokenRequest;
import com.primebank.blog.auth.jwt.model.CreateJwTokenResult;
import com.primebank.blog.auth.jwt.model.JwToken;
import com.primebank.blog.auth.jwt.model.JwTokenDecipherRequest;

public interface JwtTokenProvider {

    String ISSUER = "Blog App";

    JwToken readToken(JwTokenDecipherRequest jwTokenDecipherRequest);

    CreateJwTokenResult createToken(CreateJwTokenRequest jwToken);
}
