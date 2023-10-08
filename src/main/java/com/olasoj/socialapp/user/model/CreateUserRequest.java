package com.olasoj.socialapp.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class CreateUserRequest {
    @NotBlank(message = "Enter password")
    @JsonProperty("password")
    private String password;

    @NotBlank(message = "Enter username")
    @JsonProperty("username")
    private String username;

    @NotBlank(message = "Enter email")
    @Email(message = "Invalid email")
    @JsonProperty("email")
    private String email;

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return email;
    }

    public CreateUserRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public CreateUserRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public CreateUserRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof CreateUserRequest otherCreateUserRequest)) return false;

        return new EqualsBuilder()
                .append(password, otherCreateUserRequest.password)
                .append(username, otherCreateUserRequest.username)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(password)
                .append(username)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("password", password)
                .append("username", username)
                .toString();
    }
}
