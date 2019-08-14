package org.fasttrackit.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperConfiguration {

    private static ObjectMapper objectMapper = new ObjectMapper();

    //static block
    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    static {
        objectMapper.registerModule(new JavaTimeModule());
    }



}
