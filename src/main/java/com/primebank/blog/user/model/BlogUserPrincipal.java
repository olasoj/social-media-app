package com.primebank.blog.user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.stream.Collectors;

public record BlogUserPrincipal(User user) implements UserDetails {
    public BlogUserPrincipal {
        Assert.notNull(user, "User cannot be null");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAccessControlLists().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAccessControlList()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
