package com.deng.seckilling.rpc.util;

import java.util.Random;

/**
 * RPC-获取随机数工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/17 14:43
 */
public class RandomUtils {

    /**
     * 获取随机字符
     *
     * @param num 随机字符的字符个数
     * @return 随机字符
     */
    public static String getRandomString(Integer num) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取随机数
     *
     * @param num 随机数位数
     * @return 随机数
     */
    public static String getRandomNum(Integer num) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
