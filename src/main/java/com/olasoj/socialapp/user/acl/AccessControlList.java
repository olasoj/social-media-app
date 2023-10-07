package com.olasoj.socialapp.user.acl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.olasoj.socialapp.user.acl.config.UserServiceAccessControlListDeserializer;
import com.olasoj.socialapp.user.acl.config.UserServiceAccessControlListSerializer;

@JsonDeserialize(using = UserServiceAccessControlListDeserializer.class)
@JsonSerialize(using = UserServiceAccessControlListSerializer.class)
public interface AccessControlList {
    String getAccessControlList();
}
