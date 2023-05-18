package com.honeyautomation.apiplayground.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public final class JsonParser {

    private JsonParser() {}

    private static final ObjectWriter objectWriter;

    static {
        objectWriter = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer().withDefaultPrettyPrinter();
    }

    public static String parse(Object object) throws JsonProcessingException {
        return objectWriter.writeValueAsString(object);
    }
}
