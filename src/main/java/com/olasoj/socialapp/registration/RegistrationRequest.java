package com.olasoj.socialapp.registration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.olasoj.socialapp.user.model.CreateUserRequest;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.StringJoiner;

public class RegistrationRequest {


    @JsonProperty("user")
    @NotNull(message = "User request is required")
    private CreateUserRequest createUserRequest;

    public RegistrationRequest(CreateUserRequest createUserRequest) {
        this.createUserRequest = createUserRequest;
    }

    public RegistrationRequest() {
    }

    public CreateUserRequest getCreateUserRequest() {
        return createUserRequest;
    }

    public void setCreateUserRequest(CreateUserRequest createUserRequest) {
        this.createUserRequest = createUserRequest;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof RegistrationRequest that)) return false;
        return Objects.equals(createUserRequest, that.createUserRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createUserRequest);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RegistrationRequest.class.getSimpleName() + "[", "]")
                .add("createUserRequest=" + createUserRequest)
                .toString();
    }
}
