package org.dshubs.odc.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Collection;

/**
 * @author create by wangxian 2022/3/1
 */
@Slf4j
public class JsonUtils {
    private static JsonUtils jsonUtils = new JsonUtils();

    private ObjectMapper objectMapper;

    private JsonUtils() {
        objectMapper = new ObjectMapper();
    }

    public static JsonUtils getInstance() {
        return jsonUtils;
    }

    public String toJson(Object object) {
        if (object == null) {
            return StringUtils.EMPTY;
        }
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("[toJson] error:", e);
        }
        return StringUtils.EMPTY;
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.error("[fromJson] error:", e);
            return null;
        }
    }

    public <T> T fromJson(String json, TypeReference<T> reference) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        try {
            return objectMapper.readValue(json, reference);
        } catch (IOException e) {
            log.error("[fromJson] error:", e);
            return null;
        }
    }

    public <T> T fromJson(String json, JavaType javaType) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }

        try {
            return objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            log.error("parse json string error:" + json, e);
            return null;
        }
    }

    public JavaType buildCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
        return objectMapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
    }

}
