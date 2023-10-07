package com.primebank.blog.auth.model.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class BlogAppUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public BlogAppUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public BlogAppUsernamePasswordAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
