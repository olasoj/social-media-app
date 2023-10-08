package com.olasoj.socialapp.follow;

import java.util.Objects;
import java.util.StringJoiner;

public class FollowRequest {
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
        if (!(obj instanceof FollowRequest that)) return false;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FollowRequest.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .toString();
    }
}
