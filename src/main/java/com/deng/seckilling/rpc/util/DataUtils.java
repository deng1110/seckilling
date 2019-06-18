package com.deng.seckilling.rpc.util;

import com.deng.seckilling.rpc.exception.RpcUtilException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * RPC-数据处理工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/24 11:30
 */
@Slf4j
public class DataUtils {

    /**
     * 实体之间的同名且同类型属性的相互转化（DO->VO  or  VO->DO）
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <S>    源对象泛型
     * @param <T>    目标对象泛型
     */
    public static <S, T> void entityTransform(S source, T target) {
        if (CheckDataUtils.isEmpty(source)) {
            throw new RpcUtilException("source can not be null");
        }
        BeanUtils.copyProperties(source, target);
    }

    /**
     * List实体之间的同名且同类型属性的相互转化（DO->VO  or  VO->DO）
     *
     * @param sourceList 源对象List
     * @param targetList 目标对象List
     * @param clzz       目标对象类型
     * @param <S>        源对象泛型
     * @param <T>        目标对象泛型
     */
    public static <S, T> void listEntityTransform(List<S> sourceList, List<T> targetList, Class<T> clzz) {
        if (CheckDataUtils.isEmpty(sourceList)) {
            throw new RpcUtilException("sourceList can not be null and can not be empty");
        }
        if (null == targetList) {
            throw new RpcUtilException("targetList can not be null");
        }
        targetList.clear();
        try {
            for (int i = 0; i < sourceList.size(); i++) {
                T t = clzz.newInstance();
                entityTransform(sourceList.get(i), t);
                targetList.add(t);
            }
        } catch (Exception e) {
            log.error("===> rpc util error:{}===", e.getMessage());
            targetList.clear();
            return;
        }
    }

    /**
     * 获取List实体中的某个属性值List
     *
     * @param tList    实体List
     * @param property 实体中的某个属性名
     * @param <T>      实体的泛型
     * @return 某个属性的List
     */
    public static <T> List getListEntityProperty(List<T> tList, String property) {
        if (CheckDataUtils.isEmpty(tList) || CheckDataUtils.isEmpty(property)) {
            return null;
        }
        List rList = new ArrayList<>();
        try {
            for (T t : tList) {
                Field[] fields = t.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.getName().equals(property)) {
                        rList.add(field.get(t));
                    }
                }
            }
        } catch (Exception e) {
            log.error("===> rpc util error:{}===", e.getMessage());
            return null;
        }
        return rList;
    }

    /**
     * Map中的key值变为全部小写
     */
    public static <T> Map<String, T> dealMapKeyToLowerCase(Map<String, T> dataRangeMap) {
        if (CheckDataUtils.isEmpty(dataRangeMap)) {
            return null;
        }
        Map<String, T> newDataRangeMap = new HashMap<>();
        Iterator<Map.Entry<String, T>> iterator = dataRangeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, T> entry = iterator.next();
            newDataRangeMap.put(entry.getKey().toLowerCase(), entry.getValue());
        }
        return newDataRangeMap;
    }

}
