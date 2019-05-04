package com.deng.seckilling.rpc.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * RPC-Md5加密工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/4 17:45
 */
@Slf4j
public class Md5Utils {

    /**
     * String类型MD5加密
     *
     * @param str 待加密字符串
     * @return 加密后的字符串
     */
    public static String encryptMd5(String str) {
        if (CheckDataUtils.isEmpty(str)) {
            return null;
        }
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
        } catch (NoSuchAlgorithmException e) {
            log.info("===>unreachable line===");
            return null;
        }
        return new BigInteger(1, md.digest()).toString(16);
    }
}
