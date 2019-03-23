package com.deng.seckilling.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;

/**
 * 操作实体的工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/24 0:25
 */
@Slf4j
public class EntityUtil {

    /**
     * 实体之间的同名且同类型属性的相互转化（DO->VO  or  VO->DO）
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <S>    源对象泛型
     * @param <T>    目标对象泛型
     */
    public static <S, T> void entityTransform(S source, T target) {
        if (isEntityEmpty(source)) {
            log.warn("===>util params empty===");
            return;
        }
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 判断实体是否为空
     *
     * @param target 待判断实体
     * @param <T>    带判断实体泛型
     * @return boolean 是否为空
     */
    public static <T> boolean isEntityEmpty(T target) {
        if (null == target) {
            log.warn("===>rpc util params empty===");
            return true;
        }
        Field[] fields = target.getClass().getDeclaredFields();
        int count = 0;
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (null == field.get(target) || "" == field.get(target).toString().trim()) {
                    count++;
                }
            }
        } catch (IllegalAccessException e) {
            log.error("===> rpc util error:{}===", e.getMessage());
            return true;
        }
        return count >= fields.length;
    }
}
