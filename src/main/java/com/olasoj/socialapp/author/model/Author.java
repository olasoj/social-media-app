package com.olasoj.socialapp.author.model;

import com.olasoj.socialapp.user.model.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Author {

    private final String userId;

    public Author(User user) {
        this.userId = user.getUserId();
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Author otherAuthor)) return false;

        return new EqualsBuilder()
                .append(userId, otherAuthor.userId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userIdr", userId)
                .toString();
    }
}
