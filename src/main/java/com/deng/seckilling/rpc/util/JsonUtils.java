package com.deng.seckilling.rpc.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

/**
 * RPC-Json工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/27 10:46
 */
@Slf4j
public final class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, Boolean.TRUE);
        //只对Bean起作用，Map、List不起作用
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private JsonUtils() {
    }

    /**
     * json转java对象
     *
     * @param json  json字符串
     * @param clazz 对象类型
     * @param <T>   对象泛型
     * @return 对象实例
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("Parse json \"" + json + "\" to object \"" + clazz.getName() + "\" error.", e);
            return null;
        }
    }

    /**
     * json转List，其中List为泛型定义
     *
     * @param json         json字符串
     * @param genericClazz List中泛型数据的真实类型
     * @param <T>          泛型
     * @return 对象List
     */
    public static <T> List<T> toGenericList(String json, Class<T> genericClazz) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, genericClazz);
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            log.error("Parse json \"" + json + "\" to generic list \"" + genericClazz.getName() + "\" error.", e);
            return Collections.emptyList();
        }
    }

    public static <G, O> O toGenericListObject(String json, Class<G> genericClazz, Class<O> clazz) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, genericClazz);
            JavaType finalJavaType = mapper.getTypeFactory().constructParametricType(clazz, javaType);
            return mapper.readValue(json, finalJavaType);
        } catch (Exception e) {
            log.error("Parse json \"" + json + "\" to generic \"" + genericClazz.getName() + "\" list object \"" + clazz.getName() + "\" error.", e);
            return null;
        }
    }

    /**
     * json转对象，其中O为目标对象，T为O中的泛型对象
     *
     * @param json         json字符串
     * @param genericClazz 目标对象O中泛型数据的真实类型
     * @param clazz        目标对象的类型
     * @param <G>          标对象O中泛型数据的的泛型
     * @param <O>          标对象O的泛型
     * @return 目标对象实例
     */
    public static <G, O> O toGenericObject(String json, Class<G> genericClazz, Class<O> clazz) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(clazz, genericClazz);
            return mapper.readValue(json, javaType);
        } catch (Exception e) {
            log.error("Parse json \"" + json + "\" to generic object \"" + genericClazz.getName() + "\" error.", e);
            return null;
        }
    }

    /**
     * java对象转json
     *
     * @param obj Java对象
     * @param <T> 对象泛型
     * @return json字符串
     */
    public static <T> String toJson(T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("Parse from object \"" + obj.getClass().getName() + "\" error.", e);
            return null;
        }
    }

}
