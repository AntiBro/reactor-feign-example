package com.example.demo.feign;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * tool functions for FallbackFactories
 */
public class FallbackFactoryHelper {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    protected void loggerFailure(String apiName, Throwable cause, String... args) {
        String argsDescription = StringUtils.join(args, ", ");
        logger.error("API FAIL: {}, args: [{}], cause：{}", apiName, argsDescription, cause == null ? "null" : cause.getMessage());
    }

    protected <T> T getObjectFromJson(String json, TypeReference<T> typeReference) {
        JavaType type = mapper.getTypeFactory().constructType(typeReference);
        Class<?> cls = type.getRawClass();
        Object nullObject;
        if (cls == Map.class) {
            nullObject = Collections.emptyMap();
        } else if (cls == List.class) {
            nullObject = Collections.emptyList();
        } else {
            nullObject = null;
        }
        if (StringUtils.isNotEmpty(json)) {
            try {
                return mapper.readValue(json, typeReference);
            } catch (IOException e) {
                return (T) nullObject;
            }
        } else {
            return (T) nullObject;
        }
    }
}
