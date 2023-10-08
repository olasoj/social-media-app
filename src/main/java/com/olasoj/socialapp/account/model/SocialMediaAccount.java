package com.olasoj.socialapp.account.model;

import com.olasoj.socialapp.audit.AuditObject;

import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

public class SocialMediaAccount implements AuditObject {
    private Long userId;
    private Long socialMediaAccountId;

    private Instant createdAt;
    private Instant updatedAt;
    private String updatedBy;
    private String createdBy;
    private Integer version;

    public SocialMediaAccount(Long userId, Long socialMediaAccountId) {
        this.userId = userId;
        this.socialMediaAccountId = socialMediaAccountId;
    }

    public SocialMediaAccount(Long userId) {
        this.userId = userId;
    }

    public SocialMediaAccount() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSocialMediaAccountId() {
        return socialMediaAccountId;
    }

    public void setSocialMediaAccountId(Long socialMediaAccountId) {
        this.socialMediaAccountId = socialMediaAccountId;
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
    public void setUpdatedAt(Instant instant) {
        this.updatedAt = instant;
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
        if (!(obj instanceof SocialMediaAccount that)) return false;
        return Objects.equals(userId, that.userId) && Objects.equals(socialMediaAccountId, that.socialMediaAccountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, socialMediaAccountId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", SocialMediaAccount.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("socialMediaAccount=" + socialMediaAccountId)
                .toString();
    }
}
