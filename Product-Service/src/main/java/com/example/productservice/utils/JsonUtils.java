package com.example.productservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonUtils {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static String toString(Object object) {
        if (object == null) {
            return "";
        }
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static <T> T toObject(String stringObject, Class<T> clazz) {
        try {
            return mapper.readValue(stringObject, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static <T> T toObject(String stringObject, TypeReference<T> type) {
        try {
            return mapper.readValue(stringObject, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static JsonNode toJsonObject(String input) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = null;
        try {
            actualObj = mapper.readTree(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return actualObj;
    }

}
