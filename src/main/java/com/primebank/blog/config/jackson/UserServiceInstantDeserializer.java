package com.primebank.blog.config.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class UserServiceInstantDeserializer extends JsonDeserializer<Instant> {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_INSTANT;

    @Override
    public Instant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TemporalAccessor temporalAccessor = dateTimeFormatter.parse(jsonParser.getText());
        return Instant.from(temporalAccessor);
    }
}
