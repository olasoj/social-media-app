package com.olasoj.socialapp.comment.model;

import com.olasoj.socialapp.audit.AuditObject;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

public class Comment implements AuditObject {

    private Long commentId;
    private String content;

    private Integer likeCount;

    private Instant createdAt;
    private Instant updatedAt;
    private String updatedBy;
    private String createdBy;
    private Integer version;
    private Long postId;


    public Comment(CommentBuilder commentBuilder) {
        this.commentId = commentBuilder.commentId;
        this.content = commentBuilder.content;
        this.likeCount = commentBuilder.likeCount;
        this.createdAt = commentBuilder.createdAt;
        this.updatedAt = commentBuilder.updatedAt;
        this.updatedBy = commentBuilder.updatedBy;
        this.createdBy = commentBuilder.createdBy;
        this.version = commentBuilder.version;
        this.postId = commentBuilder.postId;
    }

    public static CommentBuilder builder() {
        return new CommentBuilder();
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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Comment post)) return false;
        return Objects.equals(commentId, post.commentId) && Objects.equals(content, post.content) && Objects.equals(likeCount, post.likeCount)
                && Objects.equals(createdAt, post.createdAt) && Objects.equals(updatedAt, post.updatedAt)
                && Objects.equals(updatedBy, post.updatedBy) && Objects.equals(createdBy, post.createdBy)
                && Objects.equals(version, post.version) && Objects.equals(postId, post.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, content, likeCount, createdAt, updatedAt, updatedBy, createdBy, version, postId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Comment.class.getSimpleName() + "[", "]")
                .add("postId=" + commentId)
                .add("content='" + content + "'")
                .add("likeCount='" + likeCount + "'")
                .add("createdAt=" + createdAt)
                .add("updatedAt=" + updatedAt)
                .add("updatedBy='" + updatedBy + "'")
                .add("createdBy='" + createdBy + "'")
                .add("version=" + version)
                .add("userId=" + postId)
                .toString();
    }

    public static class CommentBuilder {
        private Long commentId;
        private String content;

        private Integer likeCount;

        private Instant createdAt;
        private Instant updatedAt;
        private String updatedBy;
        private String createdBy;
        private Integer version;
        private Long postId;

        public CommentBuilder commentId(Long postId) {
            this.commentId = postId;
            return this;
        }

        public CommentBuilder content(String content) {
            this.content = content;
            return this;
        }

        public CommentBuilder likeCount(Integer likeCount) {
            this.likeCount = likeCount;
            return this;
        }

        public CommentBuilder createdAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CommentBuilder updatedAt(Instant updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public CommentBuilder updatedBy(String updatedBy) {
            this.updatedBy = updatedBy;
            return this;
        }

        public CommentBuilder createdBy(String createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public CommentBuilder version(Integer version) {
            this.version = version;
            return this;
        }

        public CommentBuilder postId(Long socialMediaAccountId) {
            this.postId = socialMediaAccountId;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }
}
