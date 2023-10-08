package com.olasoj.socialapp.registration;

import com.olasoj.socialapp.user.model.CreateUserRequest;

import java.util.Objects;
import java.util.StringJoiner;

public class RegistrationRequest {

    private CreateUserRequest createUserRequest;

    public RegistrationRequest(CreateUserRequest createUserRequest) {
        this.createUserRequest = createUserRequest;
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
