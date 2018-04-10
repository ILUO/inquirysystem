package com.gluxen.util;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Yang Xing Luo on 2018/1/12.
 */
public class formatJsonUtil {

    public static <T> T readJson(String jsonStr,Class<T> collectionClass,Class<?>... elementClass) throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        JavaType javatype = mapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
        return mapper.readValue(jsonStr, javatype);
    }
}
