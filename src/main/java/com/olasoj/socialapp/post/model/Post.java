package com.olasoj.socialapp.post.model;

import com.olasoj.socialapp.audit.AuditObject;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

public class Post implements AuditObject {

    private Long postId;
    private String content;

    private Integer likeCount;

    private Instant createdAt;
    private Instant updatedAt;
    private String updatedBy;
    private String createdBy;
    private Integer version;
    private Long socialMediaAccountId;


    public Post(PostBuilder postBuilder) {
        this.postId = postBuilder.postId;
        this.content = postBuilder.content;
        this.likeCount = postBuilder.likeCount;
        this.createdAt = postBuilder.createdAt;
        this.updatedAt = postBuilder.updatedAt;
        this.updatedBy = postBuilder.updatedBy;
        this.createdBy = postBuilder.createdBy;
        this.version = postBuilder.version;
        this.socialMediaAccountId = postBuilder.socialMediaAccountId;
    }

    public static PostBuilder builder() {
        return new PostBuilder();
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
    public void setUpdatedAt(Instant instant) {
        this.updatedAt = instant;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Long getSocialMediaAccountId() {
        return socialMediaAccountId;
    }

    public void setSocialMediaAccountId(Long socialMediaAccountId) {
        this.socialMediaAccountId = socialMediaAccountId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Post post)) return false;
        return Objects.equals(postId, post.postId) && Objects.equals(content, post.content) && Objects.equals(likeCount, post.likeCount)
                && Objects.equals(createdAt, post.createdAt) && Objects.equals(updatedAt, post.updatedAt)
                && Objects.equals(updatedBy, post.updatedBy) && Objects.equals(createdBy, post.createdBy)
                && Objects.equals(version, post.version) && Objects.equals(socialMediaAccountId, post.socialMediaAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, content, likeCount, createdAt, updatedAt, updatedBy, createdBy, version, socialMediaAccountId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Post.class.getSimpleName() + "[", "]")
                .add("postId=" + postId)
                .add("content='" + content + "'")
                .add("likeCount='" + likeCount + "'")
                .add("createdAt=" + createdAt)
                .add("updatedAt=" + updatedAt)
                .add("updatedBy='" + updatedBy + "'")
                .add("createdBy='" + createdBy + "'")
                .add("version=" + version)
                .add("userId=" + socialMediaAccountId)
                .toString();
    }

    public static class PostBuilder {
        private Long postId;
        private String content;

        private Integer likeCount;

        private Instant createdAt;
        private Instant updatedAt;
        private String updatedBy;
        private String createdBy;
        private Integer version;
        private Long socialMediaAccountId;

        public PostBuilder postId(Long postId) {
            this.postId = postId;
            return this;
        }

        public PostBuilder content(String content) {
            this.content = content;
            return this;
        }

        public PostBuilder likeCount(Integer likeCount) {
            this.likeCount = likeCount;
            return this;
        }

        public PostBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PostBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public PostBuilder updatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public PostBuilder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public PostBuilder version(Integer version) {
            this.version = version;
            return this;
        }

        public PostBuilder socialMediaAccountId(Long socialMediaAccountId) {
            this.socialMediaAccountId = socialMediaAccountId;
            return this;
        }

        public Post build() {
            return new Post(this);
        }
    }
}
