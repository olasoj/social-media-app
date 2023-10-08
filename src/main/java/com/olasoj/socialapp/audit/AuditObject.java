package com.olasoj.socialapp.audit;

import java.time.Instant;

public interface AuditObject {

    void setCreatedAt(Instant instant);
    void setUpdatedAt(Instant instant);
    void setUpdatedBy(String identifier);
    void setCreatedBy(String identifier);

    Instant getCreatedAt();
    String getCreatedBy();
    Instant getUpdatedAt();
    String getUpdatedBy();
}
