package com.primebank.blog.user.acl;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.primebank.blog.user.acl.config.UserServiceAccessControlListDeserializer;
import com.primebank.blog.user.acl.config.UserServiceAccessControlListSerializer;

@JsonDeserialize(using = UserServiceAccessControlListDeserializer.class)
@JsonSerialize(using = UserServiceAccessControlListSerializer.class)
public interface AccessControlList {
    String getAccessControlList();
}
