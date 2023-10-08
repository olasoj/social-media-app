package com.olasoj.socialapp.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olasoj.socialapp.user.model.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RegistrationResult {

    @JsonProperty("user")
    private final String message;

    public RegistrationResult(String newUser) {
        this.message = newUser;
    }

    public String user() {
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof RegistrationResult otherRegistrationResult)) return false;

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
