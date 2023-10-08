package com.olasoj.socialapp.account.model;

import java.util.Objects;
import java.util.StringJoiner;

public class CreateNewSocialAccountRequest {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CreateNewSocialAccountRequest that)) return false;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CreateNewSocialAccountRequest.class.getSimpleName() + "[", "]")
                .add("userId=" + username)
                .toString();
    }
}
