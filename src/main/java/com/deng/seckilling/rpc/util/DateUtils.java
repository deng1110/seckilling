package com.deng.seckilling.rpc.util;

import com.deng.seckilling.rpc.exception.RpcUtilException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * RPC-日期类型的工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/17 14:57
 */
public class DateUtils {

    /**
     * yyyy-MM-dd HH:mm:ss格式的日期转换成Date
     *
     * @param source yyyy-MM-dd HH:mm:ss
     * @return Date
     */
    public static Date strToDate(String source) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(source);
        } catch (Exception e) {
            throw new RpcUtilException("StrToDate params need 'yyyy-MM-dd HH:mm:ss' format value.", e);
        }
    }

    /**
     * Date日期格式转成yyyy-MM-dd HH:mm:ss格式
     *
     * @param date 日期
     * @return 格式化后的格式yyy-MM-dd HH:mm:ss
     */
    public static String dateToStr(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * 和当前时间比较是否超时
     *
     * @param str 待比较时间，格式：2019-03-25 19:13:06
     * @return boolean 是否超时
     */
    public static boolean isExpire(String str) {
        if (CheckDataUtils.isEmpty(str)) {
            throw new RpcUtilException("isNotExpire params empty Exception");
        }
        return new Date().after(strToDate(str));
    }

    /**
     * 和当前时间比较是否超时
     *
     * @param date 待比较时间，Date类型
     * @return boolean 是否超时
     */
    public static boolean isExpire(Date date) {
        if (CheckDataUtils.isEmpty(date)) {
            throw new RpcUtilException("isNotExpire params empty Exception");
        }
        return new Date().after(date);
    }

    /**
     * 获取过期时间
     *
     * @param expireTime 目标过期时间,例如：2019-03-25 11:25:12
     * @return 还剩多少秒过期
     */
    public static Long getExpireTime(String expireTime) {
        if (CheckDataUtils.isEmpty(expireTime)) {
            throw new RpcUtilException("getExpireTime params empty Exception");
        }
        if (isExpire(expireTime)) {
            throw new RpcUtilException("this param expireTime has already expire");
        }
        return (strToDate(expireTime).getTime() - System.currentTimeMillis()) / 1000;
    }

    /**
     * 获取过期时间
     *
     * @param expireTime 目标过期时间,例如：2019-03-25 11:25:12
     * @return 还剩多少秒过期(- 1 : 已经过期)
     */
    public static Long getExpireTime(Date expireTime) {
        if (CheckDataUtils.isEmpty(expireTime)) {
            throw new RpcUtilException("getExpireTime params empty Exception");
        }
        if (isExpire(expireTime)) {
            return -1L;
        }
        return (expireTime.getTime() - System.currentTimeMillis()) / 1000;
    }

    /**
     * 设置过期时间
     *
     * @param expireTime Date类型
     * @param minute     增加的分钟数，例如：10
     * @return String类型的新过期时间，例如：2019-03-25 11:25:12
     */
    public static String setExpireTime(Date expireTime, Long minute) {
        if (CheckDataUtils.isEmpty(expireTime) || CheckDataUtils.isEmpty(minute)) {
            throw new RpcUtilException("setExpireTime params empty Exception");
        }
        Date newExpireTime = new Date(expireTime.getTime() + (minute * 60 * 1000));
        return dateToStr(newExpireTime);
    }

    /**
     * expireTime是否在minute分钟后过期
     *
     * @param expireTime 待判断时间,例如：2019-03-25 11:25:12
     * @param minute     指定分钟数
     * @return 是否在指定minute之內过期
     */
    public static boolean isOverByMinute(String expireTime, Long minute) {
        return getExpireTime(expireTime) > minute * 60;
    }

}
