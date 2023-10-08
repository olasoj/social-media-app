package com.olasoj.socialapp.post.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public record EditPostResult(Blog blog) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof EditPostResult otherEditPostResult)) return false;

        return new EqualsBuilder()
                .append(blog, otherEditPostResult.blog)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(blog)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("blog", blog)
                .toString();
    }
}