package com.deng.seckilling.rpc.constant;

/**
 * RPC-枚举类型接口
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/24 0:03
 */
public interface BaseEnum {
    /**
     * 枚举类的code值
     *
     * @return
     */
    Integer getCode();

    /**
     * 枚举类的value值
     *
     * @return
     */
    String getValue();

    /**
     * 枚举类的描述
     *
     * @return
     */
    String getDesc();
}
