package com.olasoj.socialapp.user.model;

import com.olasoj.socialapp.audit.AuditObject;
import com.olasoj.socialapp.user.acl.AccessControlList;

import java.util.List;

public interface User extends AuditObject {

    Long getUserId();

    String getUsername();
    String getEmail();

    String getPassword();

    String getProfilePhoto();

    List<AccessControlList> getAccessControlLists();
}
