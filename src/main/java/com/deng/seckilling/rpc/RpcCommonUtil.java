package com.deng.seckilling.rpc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * rpc工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/24 11:30
 */
@Slf4j
public class RpcCommonUtil {

    public static boolean isEmpty(Long target) {
        return null == target || 0 >= target;
    }

    public static boolean isEmpty(Integer target) {
        return null == target || 0 >= target;
    }

    public static boolean isEmpty(Date target) {
        return null == target;
    }

    public static boolean isEmpty(Double target) {
        return null == target || 0 >= target;
    }

    public static boolean isEmpty(String target) {
        return null == target || target.isEmpty();
    }

    public static <K, V> boolean isEmpty(Map<K, V> target) {
        return null == target || target.isEmpty();
    }

    public static <T> boolean isEmpty(List<T> target) {
        return null == target || target.isEmpty();
    }

    /**
     * 判断实体是否为空
     *
     * @param target 待判断实体
     * @param <T>    带判断实体泛型
     * @return boolean 是否为空
     */
    public static <T> boolean isEmpty(T target) {
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

    /**
     * 实体之间的同名且同类型属性的相互转化（DO->VO  or  VO->DO）
     *
     * @param source 源对象
     * @param target 目标对象
     * @param <S>    源对象泛型
     * @param <T>    目标对象泛型
     */
    public static <S, T> void entityTransform(S source, T target) {
        if (isEmpty(source)) {
            log.warn("===>util params empty===");
            return;
        }
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 判断code值是否存在于枚举类型中
     *
     * @param enumClzz 枚举接口实现类的类型
     * @param code     枚举类型的code值
     * @param <T>      枚举接口的实现类的泛型
     * @return boolean 是否存在
     */
    public static <T extends BaseEnum> boolean isEnumCode(Class<T> enumClzz, Integer code) {
        if (isEmpty(code)) {
            log.warn("===>rpc util params empty===");
            return false;
        }
        for (T t : enumClzz.getEnumConstants()) {
            if (t.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据code值获取枚举类型的对应value
     *
     * @param enumClzz 枚举接口实现类的类型
     * @param code     枚举类型的code值
     * @param <T>      枚举接口的实现类的泛型
     * @return 枚举类型中的value值
     */
    public static <T extends BaseEnum> String getEnumValueByCode(Class<T> enumClzz, Integer code) {
        if (isEmpty(code)) {
            log.warn("===>rpc util params empty===");
            return null;
        }
        for (T t : enumClzz.getEnumConstants()) {
            if (t.getCode().equals(code)) {
                return t.getValue();
            }
        }
        return null;
    }

    /**
     * String类型MD5加密
     *
     * @param str 待加密字符串
     * @return 加密后的字符串
     * @throws Exception 会抛出异常调用方处理
     */
    public static String encryptMd5(String str) {
        if (isEmpty(str)) {
            log.warn("===>rpc util params empty===");
            return null;
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.info("===>unreachable line===");
            return null;
        }
        md.update(str.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }

}
