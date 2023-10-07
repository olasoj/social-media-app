package com.olasoj.socialapp.user.model;

import com.olasoj.socialapp.user.acl.AccessControlList;

import java.util.List;

public interface User {

    String getUserId();

    String getUsername();

    String getPassword();

    List<AccessControlList> getAccessControlLists();
}
