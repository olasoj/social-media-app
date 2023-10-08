package com.olasoj.socialapp.follow;

import com.olasoj.socialapp.audit.AuditObject;
import com.olasoj.socialapp.follow.repository.FollowStatus;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

public class FollowRelationShip implements AuditObject {
    private Long followId;
    private Long socialMediaAccountId;

    private Long followSocialMediaAccountId;
    private FollowStatus followStatus;

    private Instant createdAt;
    private Instant updatedAt;
    private String updatedBy;
    private String createdBy;
    private Integer version;

    public FollowRelationShip(Long userId, Long socialMediaAccountId) {
        this.followId = userId;
        this.socialMediaAccountId = socialMediaAccountId;
    }

    public FollowRelationShip(Long userId) {
        this.followId = userId;
    }

    public FollowRelationShip() {

    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }

    public Long getSocialMediaAccountId() {
        return socialMediaAccountId;
    }

    public void setSocialMediaAccountId(Long socialMediaAccountId) {
        this.socialMediaAccountId = socialMediaAccountId;
    }

    public Long getFollowSocialMediaAccountId() {
        return followSocialMediaAccountId;
    }

    public void setFollowSocialMediaAccountId(Long followSocialMediaAccountId) {
        this.followSocialMediaAccountId = followSocialMediaAccountId;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FollowRelationShip)) return false;
        FollowRelationShip that = (FollowRelationShip) obj;
        return Objects.equals(followId, that.followId) && Objects.equals(socialMediaAccountId, that.socialMediaAccountId) && Objects.equals(followSocialMediaAccountId, that.followSocialMediaAccountId) && followStatus == that.followStatus && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(updatedBy, that.updatedBy) && Objects.equals(createdBy, that.createdBy) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(followId, socialMediaAccountId, followSocialMediaAccountId, followStatus, createdAt, updatedAt, updatedBy, createdBy, version);
    }

    public FollowStatus getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(FollowStatus followStatus) {
        this.followStatus = followStatus;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FollowRelationShip.class.getSimpleName() + "[", "]")
                .add("followId=" + followId)
                .add("socialMediaAccountId=" + socialMediaAccountId)
                .add("followSocialMediaAccountId=" + followSocialMediaAccountId)
                .add("followStatus=" + followStatus)
                .add("createdAt=" + createdAt)
                .add("updatedAt=" + updatedAt)
                .add("updatedBy='" + updatedBy + "'")
                .add("createdBy='" + createdBy + "'")
                .add("version=" + version)
                .toString();
    }
}
