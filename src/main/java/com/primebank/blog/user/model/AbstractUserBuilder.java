package com.primebank.blog.user.model;

import com.primebank.blog.user.acl.AccessControlList;

import java.util.List;

public abstract class AbstractUserBuilder<C extends User, B extends AbstractUserBuilder<C, B>> {

    protected List<AccessControlList> accessControlList;
    protected String username;
    protected String password;

    protected AbstractUserBuilder() {
    }

    protected abstract B self();

    public abstract C build();

    public B username(String username) {
        this.username = username;
        return this.self();
    }

    public B password(String password) {
        this.password = password;
        return this.self();
    }

    public B accessControlList(List<AccessControlList> accessControlLists) {
        this.accessControlList = accessControlLists;
        return this.self();
    }

}
