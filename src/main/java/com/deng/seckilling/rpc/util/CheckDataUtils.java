package com.deng.seckilling.rpc.util;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * RPC-数据校验
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/4 16:20
 */
public class CheckDataUtils {

    public static boolean isEmpty(Long target) {
        return null == target || 0 >= target;
    }

    public static boolean isEmpty(Integer target) {
        return null == target || 0 >= target;
    }

    public static boolean isEmpty(Double target) {
        return null == target || 0 >= target;
    }

    public static boolean isEmpty(String target) {
        return null == target || target.isEmpty();
    }

    public static boolean isEmpty(Date target) {
        return null == target;
    }

    public static <K, V> boolean isEmpty(Map<K, V> target) {
        return null == target || target.isEmpty();
    }

    public static <T> boolean isEmpty(List<T> target) {
        return null == target || target.isEmpty();
    }

    public static <T> boolean isEmpty(T target) {
        return null == target;
    }
}
