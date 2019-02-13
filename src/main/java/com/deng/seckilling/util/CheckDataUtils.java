package com.deng.seckilling.util;

import com.deng.seckilling.constant.DefaultValue;
import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * 验证各种数据是否为空（true:为空；false：非空）
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 15:12
 */
public class CheckDataUtils<T> {

    public static boolean isEmpty(Long target) {
        return null == target || 0 >= target;
    }

    public static boolean isExit(Long target) {
        return null == target;
    }

    public static boolean isEmpty(Integer target) {
        return null == target || 0 >= target;
    }

    public static boolean isExit(Integer target) {
        return null == target;
    }

    public static boolean isEmpty(Date date) {
        return null == date;
    }

    public static boolean isExit(Date date) {
        return null == date;
    }

    public static boolean isEmpty(String target) {
        return null == target || target.isEmpty();
    }

    public static boolean isExit(String target) {
        return null == target;
    }

    public static <T> boolean isEmpty(List<T> list) {
        return null == list || list.isEmpty();
    }

    public static <T> boolean isEmpty(Map<String, T> map) {
        return null == map || map.isEmpty();
    }

    /**
     * 判断 对象中所有属性是否均为空
     *
     * @param target 目标对象
     * @param <T>    泛型类型
     * @return true：均为空；false：非均为空
     * @throws IllegalAccessException 需要调用者捕获异常
     */
    public static <T> boolean isEmpty(T target) throws IllegalAccessException {
        if (null == target) {
            return true;
        }
        Field[] fields = target.getClass().getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (null != f.get(target) && "" != f.get(target) && false == "".equals(f.get(target))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isRank(String rank) {
        return (false == isEmpty(rank)) && (DefaultValue.RANK_VALUE_ADMIN.equals(rank) || DefaultValue.RANK_VALUE_BUYER.equals(rank) || DefaultValue.RANK_VALUE_SELLER.equals(rank));
    }

    public static boolean isSex(String sex) {
        return (false == isEmpty(sex)) && (DefaultValue.SEX_VALUE_FEMALE.equals(sex) || DefaultValue.SEX_VALUE_MALE.equals(sex));
    }
}
