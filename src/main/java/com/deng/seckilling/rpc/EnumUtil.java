package com.deng.seckilling.rpc;

import lombok.extern.slf4j.Slf4j;

/**
 * 枚举工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/24 0:17
 */
@Slf4j
public class EnumUtil {

    /**
     * 判断code值是否存在于枚举类型中
     *
     * @param enumClzz 枚举接口实现类的类型
     * @param code     枚举类型的code值
     * @param <T>      枚举接口的实现类的泛型
     * @return boolean 是否存在
     */
    public static <T extends BaseEnum> boolean isEnumCode(Class<T> enumClzz, Integer code) {
        if (null == code) {
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
     *
     * @param enumClzz 枚举接口实现类的类型
     * @param code     枚举类型的code值
     * @param <T>      枚举接口的实现类的泛型
     * @return 枚举类型中的value值
     */
    public static <T extends BaseEnum> String getEnumValueByCode(Class<T> enumClzz, Integer code) {
        if (null == code) {
            log.warn("===>rpc util params empty===");
            return null;
        }
        for (T t : enumClzz.getEnumConstants()) {
            if (t.getCode() == code) {
                return t.getValue();
            }
        }
        return null;
    }

}
