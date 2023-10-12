package com.honey.apiplayground.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class JsonParser {

    private JsonParser() {}

    private static final ObjectMapper objectWriter = new ObjectMapper();

    public static String parse(Object object) throws JsonProcessingException {
        return objectWriter.writeValueAsString(object);
    }
}
