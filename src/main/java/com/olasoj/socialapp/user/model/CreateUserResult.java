package com.olasoj.socialapp.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class CreateUserResult {

    @JsonProperty("user")
    private final String message;

    public CreateUserResult(String newUser) {
        this.message = newUser;
    }

    public String user() {
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof CreateUserResult otherRegistrationResult)) return false;

        return new EqualsBuilder()
                .append(message, otherRegistrationResult.message)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(message)
                .toHashCode();
    }
}
