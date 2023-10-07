package com.primebank.blog.user.model;

import com.primebank.blog.user.acl.AccessControlList;

import java.util.List;

public interface User {

    String getUserId();

    String getUsername();

    String getPassword();

    List<AccessControlList> getAccessControlLists();
}
