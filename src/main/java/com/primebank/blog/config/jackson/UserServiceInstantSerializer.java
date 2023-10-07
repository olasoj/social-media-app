package com.primebank.blog.config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class UserServiceInstantSerializer extends JsonSerializer<Instant> {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_INSTANT;

    @Override
    public void serialize(Instant value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String str = dateTimeFormatter.format(value);
        gen.writeString(str);
    }
}
