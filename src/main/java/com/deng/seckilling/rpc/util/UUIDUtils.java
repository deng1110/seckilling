package com.deng.seckilling.rpc.util;

import java.util.UUID;

/**
 * EPC-UUID工具类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/27 10:29
 */
public class UUIDUtils {

    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
