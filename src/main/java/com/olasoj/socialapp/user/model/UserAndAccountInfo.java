package com.olasoj.socialapp.user.model;

import java.util.Objects;
import java.util.StringJoiner;

public class UserAndAccountInfo {

    private User user;
    private Long socialAccountId;

    public UserAndAccountInfo(User user, Long socialAccountId) {
        this.user = user;
        this.socialAccountId = socialAccountId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setSocialAccountId(Long socialAccountId) {
        this.socialAccountId = socialAccountId;
    }

    public Long getSocialAccountId() {
        return socialAccountId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserAndAccountInfo)) return false;
        UserAndAccountInfo that = (UserAndAccountInfo) obj;
        return Objects.equals(user, that.user) && Objects.equals(socialAccountId, that.socialAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, socialAccountId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserAndAccountInfo.class.getSimpleName() + "[", "]")
                .add("user=" + user)
                .add("socialAccountId=" + socialAccountId)
                .toString();
    }
}


