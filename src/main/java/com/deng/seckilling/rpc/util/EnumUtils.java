package com.deng.seckilling.rpc.util;

import com.deng.seckilling.rpc.constant.BaseEnum;
import com.deng.seckilling.rpc.exception.RpcUtilException;

/**
 * RPC-枚举工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/4 16:28
 */
public class EnumUtils {

    /**
     * 判断code值是否存在于枚举类型中
     *
     * @param enumClzz 枚举接口实现类的类型
     * @param code     枚举类型的code值
     * @param <T>      枚举接口的实现类的泛型
     * @return boolean 是否存在
     */
    public static <T extends BaseEnum> boolean isEnumCode(Class<T> enumClzz, Integer code) {
        if (null == enumClzz) {
            throw new RpcUtilException("target class type can not be null");
        }
        if (CheckDataUtils.isEmpty(code)) {
            throw new RpcUtilException("target code can not be null and value must be greater than zero ");
        }
        for (T t : enumClzz.getEnumConstants()) {
            if (t.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断value值是否存在于枚举类型中
     *
     * @param enumClzz 枚举接口实现类的类型
     * @param value    枚举类型的value值
     * @param <T>      枚举接口的实现类的泛型
     * @return boolean 是否存在
     */
    public static <T extends BaseEnum> boolean isEnumValue(Class<T> enumClzz, String value) {
        if (null == enumClzz) {
            throw new RpcUtilException("target class type can not be null");
        }
        if (CheckDataUtils.isEmpty(value)) {
            throw new RpcUtilException("target value can not be null");
        }
        for (T t : enumClzz.getEnumConstants()) {
            if (t.getValue().equals(value)) {
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
        if (null == enumClzz) {
            throw new RpcUtilException("target class type can not be null");
        }
        if (CheckDataUtils.isEmpty(code)) {
            throw new RpcUtilException("target code can not be null and value must be greater than zero ");
        }
        for (T t : enumClzz.getEnumConstants()) {
            if (t.getCode().equals(code)) {
                return t.getValue();
            }
        }
        return null;
    }

    /**
     * 根据value值获取枚举类型的对应code
     *
     * @param enumClzz 枚举接口实现类的类型
     * @param value    枚举类型的value值
     * @param <T>      枚举接口的实现类的泛型
     * @return 枚举类型中的code值
     */
    public static <T extends BaseEnum> Integer getEnumCodeByValue(Class<T> enumClzz, String value) {
        if (null == enumClzz) {
            throw new RpcUtilException("target class type can not be null");
        }
        if (CheckDataUtils.isEmpty(value)) {
            throw new RpcUtilException("target value can not be null");
        }
        for (T t : enumClzz.getEnumConstants()) {
            if (t.getValue().equals(value)) {
                return t.getCode();
            }
        }
        return null;
    }

}
