package com.olasoj.socialapp.blogpost.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public record ReadBlogPostResult(List<Blog> blogPosts) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof ReadBlogPostResult otherReadBlogPostResult)) return false;

        return new EqualsBuilder().append(blogPosts, otherReadBlogPostResult.blogPosts).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(blogPosts).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("blogPosts", blogPosts)
                .toString();
    }
}
