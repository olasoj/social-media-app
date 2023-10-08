package com.olasoj.socialapp.post.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public record ReadPostsResult(PostWithPageInfoResult allPost) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof ReadPostsResult otherReadPostsResult)) return false;

        return new EqualsBuilder().append(allPost, otherReadPostsResult.allPost).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(allPost).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("allPost", allPost)
                .toString();
    }
}
