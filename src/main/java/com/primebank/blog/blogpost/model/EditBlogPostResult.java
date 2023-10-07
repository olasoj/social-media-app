package com.primebank.blog.blogpost.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public record EditBlogPostResult(Blog blog) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof EditBlogPostResult otherEditBlogPostResult)) return false;

        return new EqualsBuilder()
                .append(blog, otherEditBlogPostResult.blog)
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
