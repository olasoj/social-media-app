package com.olasoj.socialapp.user.model;

import com.olasoj.socialapp.user.acl.AccessControlList;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class BlogUserView {
    private final String email;
    private final String profilePhoto;
    private final int version;
    private final long userId;
    private final String username;
    private final List<AccessControlList> accessControlList;
    private final Instant createdAt;
    private final Instant updatedAt;
    private final String updatedBy;
    private final String createdBy;

    public BlogUserView(BlogUser blogUser) {
        Assert.notNull(blogUser, "BlogUser cannot be null");

        this.userId = blogUser.getUserId();
        this.username = blogUser.getUsername();
        this.accessControlList = blogUser.getAccessControlLists();

        this.email = blogUser.getEmail();

        this.createdAt = blogUser.getCreatedAt();
        this.updatedAt = blogUser.getUpdatedAt();
        this.updatedBy = blogUser.getUpdatedBy();
        this.createdBy = blogUser.getCreatedBy();
        this.profilePhoto = blogUser.getProfilePhoto();
        this.version = blogUser.getVersion();
    }

    public Long getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public List<AccessControlList> getAccessControlLists() {
        return Objects.isNull(accessControlList) ? Collections.emptyList() : accessControlList;
    }

    public String getEmail() {
        return email;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public int getVersion() {
        return version;
    }

    public List<AccessControlList> getAccessControlList() {
        return accessControlList;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BlogUserView blogUser)) return false;
        return version == blogUser.version && userId == blogUser.userId
                && Objects.equals(email, blogUser.email) && Objects.equals(profilePhoto, blogUser.profilePhoto)
                && Objects.equals(username, blogUser.username)
                && Objects.equals(accessControlList, blogUser.accessControlList) && Objects.equals(createdAt, blogUser.createdAt)
                && Objects.equals(updatedAt, blogUser.updatedAt) && Objects.equals(updatedBy, blogUser.updatedBy) && Objects.equals(createdBy, blogUser.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, profilePhoto, version, userId, username, accessControlList, createdAt, updatedAt, updatedBy, createdBy);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BlogUserView.class.getSimpleName() + "[", "]")
                .add("email='" + email + "'")
                .add("profilePhoto='" + profilePhoto + "'")
                .add("version=" + version)
                .add("userId=" + userId)
                .add("username='" + username + "'")
                .add("accessControlList=" + accessControlList)
                .add("createdAt=" + createdAt)
                .add("updatedAt=" + updatedAt)
                .add("updatedBy='" + updatedBy + "'")
                .add("createdBy='" + createdBy + "'")
                .toString();
    }

}
