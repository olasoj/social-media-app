package com.olasoj.socialapp.comment.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public record ReadCommentResult(Comment post) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof ReadCommentResult otherReadPostsResult)) return false;

        return new EqualsBuilder().append(post, otherReadPostsResult.post).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(post).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("post", post)
                .toString();
    }
}
