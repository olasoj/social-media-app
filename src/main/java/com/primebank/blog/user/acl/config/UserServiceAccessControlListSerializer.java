package com.primebank.blog.user.acl.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.primebank.blog.user.acl.AccessControlList;

import java.io.IOException;

public class UserServiceAccessControlListSerializer extends JsonSerializer<AccessControlList> {


    @Override
    public void serialize(AccessControlList value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.getAccessControlList());
    }
}
