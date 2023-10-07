package com.primebank.blog.user.acl.role;

import com.primebank.blog.user.acl.AccessControlList;

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
