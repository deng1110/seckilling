package com.deng.seckilling.util;

import com.deng.seckilling.constant.DefaultValue;

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

    public static boolean isEmpty(Date date) {
        return null == date;
    }

    public static boolean isEmpty(String target) {
        return null == target || target.isEmpty();
    }

    public static <T> boolean isEmpty(List<T> list) {
        return null == list || list.isEmpty();
    }

    public static <T> boolean isEmpty(Map<String, T> map) {
        return null == map || map.isEmpty();
    }

    public static boolean isRank(String rank) {
        return (false == isEmpty(rank)) && (DefaultValue.RANK_VALUE_ADMIN.equals(rank) || DefaultValue.RANK_VALUE_BUYER.equals(rank) || DefaultValue.RANK_VALUE_SELLER.equals(rank));
    }

    public static boolean isSex(String sex) {
        return (false == isEmpty(sex)) && (DefaultValue.SEX_VALUE_FEMALE.equals(sex) || DefaultValue.SEX_VALUE_MALE.equals(sex));
    }
}
