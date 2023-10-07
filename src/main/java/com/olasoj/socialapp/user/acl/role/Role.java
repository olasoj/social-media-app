package com.olasoj.socialapp.user.acl.role;

import com.olasoj.socialapp.user.acl.AccessControlList;

public enum Role implements AccessControlList {
    READ("READ"), WRITE("WRITE");

    private final String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getAccessControlList() {
        return roleName;
    }
}
