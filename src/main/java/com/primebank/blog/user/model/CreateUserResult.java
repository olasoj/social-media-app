package com.primebank.blog.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CreateUserResult {

    @JsonProperty("user")
    private final User user;

    public CreateUserResult(User newUser) {
        this.user = newUser;
    }

    public User user() {
        return user;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof CreateUserResult otherCreateUserResult)) return false;

        return new EqualsBuilder()
                .append(user, otherCreateUserResult.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(user)
                .toHashCode();
    }
}
