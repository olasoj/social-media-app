package com.olasoj.socialapp.user.model;

import com.olasoj.socialapp.user.acl.AccessControlList;
import org.springframework.util.Assert;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class BlogUser implements User {
    private final String email;
    private final String profilePhoto;
    private final int version;
    private long userId;
    private String username;
    private String password;
    private List<AccessControlList> accessControlList;
    private Instant createdAt;
    private Instant updatedAt;
    private String updatedBy;
    private String createdBy;

    public BlogUser(BlogUserBuilder blogUserBuilder) {
        Assert.notNull(blogUserBuilder, "Blog builder cannot be null");

        this.userId = blogUserBuilder.userId;

        Assert.notNull(blogUserBuilder.username, "User username cannot be null");
        this.username = blogUserBuilder.username;

        Assert.notNull(blogUserBuilder.password, "User password cannot be null");
        this.password = blogUserBuilder.password;

        Assert.notNull(blogUserBuilder.accessControlList, "User accessControlList cannot be null");
        this.accessControlList = blogUserBuilder.accessControlList;

        Assert.notNull(blogUserBuilder.email, "User email cannot be null");
        this.email = blogUserBuilder.email;

        this.createdAt = blogUserBuilder.createdAt;
        this.updatedAt = blogUserBuilder.updatedAt;
        this.updatedBy = blogUserBuilder.updatedBy;
        this.createdBy = blogUserBuilder.createdBy;
        this.profilePhoto = blogUserBuilder.profilePhoto;
        this.version = blogUserBuilder.version;
    }


    public static BlogUserBuilder builder() {
        return new BlogUserBuilder();
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    public BlogUser setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public BlogUser setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public BlogUser setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public List<AccessControlList> getAccessControlLists() {
        return Objects.isNull(accessControlList) ? Collections.emptyList() : accessControlList;
    }

    @Override
    public void setUpdatedAt(Instant instant) {
        this.updatedAt = instant;
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

    public BlogUser setAccessControlList(List<AccessControlList> accessControlList) {
        this.accessControlList = accessControlList;
        return this;
    }

    @Override
    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public void setCreatedAt(Instant instant) {
        this.createdAt = instant;
    }

    @Override
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String identifier) {
        this.updatedBy = identifier;
    }

    @Override
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String identifier) {
        this.createdBy = identifier;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BlogUser blogUser)) return false;
        return version == blogUser.version && userId == blogUser.userId
                && Objects.equals(email, blogUser.email) && Objects.equals(profilePhoto, blogUser.profilePhoto)
                && Objects.equals(username, blogUser.username) && Objects.equals(password, blogUser.password)
                && Objects.equals(accessControlList, blogUser.accessControlList) && Objects.equals(createdAt, blogUser.createdAt)
                && Objects.equals(updatedAt, blogUser.updatedAt) && Objects.equals(updatedBy, blogUser.updatedBy) && Objects.equals(createdBy, blogUser.createdBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, profilePhoto, version, userId, username, password, accessControlList, createdAt, updatedAt, updatedBy, createdBy);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", BlogUser.class.getSimpleName() + "[", "]")
                .add("email='" + email + "'")
                .add("profilePhoto='" + profilePhoto + "'")
                .add("version=" + version)
                .add("userId=" + userId)
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("accessControlList=" + accessControlList)
                .add("createdAt=" + createdAt)
                .add("updatedAt=" + updatedAt)
                .add("updatedBy='" + updatedBy + "'")
                .add("createdBy='" + createdBy + "'")
                .toString();
    }

    public static class BlogUserBuilder {
        private long userId;
        private String username;
        private String email;
        private String password;
        private String profilePhoto;
        private List<AccessControlList> accessControlList;
        private Instant createdAt;
        private Instant updatedAt;
        private String updatedBy;
        private String createdBy;

        private int version;


        public BlogUserBuilder userId(long userId) {
            this.userId = userId;
            return this;
        }

        public BlogUserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public BlogUserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public BlogUserBuilder password(String password) {
            this.password = password;
            return this;
        }

        public BlogUserBuilder profilePhoto(String profilePhoto) {
            this.profilePhoto = profilePhoto;
            return this;
        }

        public BlogUserBuilder accessControlList(List<AccessControlList> accessControlList) {
            this.accessControlList = accessControlList;
            return this;
        }

        public BlogUserBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public BlogUserBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public BlogUserBuilder updatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public BlogUserBuilder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public BlogUserBuilder version(int version) {
            this.version = version;
            return this;
        }

        public BlogUser build() {
            return new BlogUser(this);
        }
    }


}
