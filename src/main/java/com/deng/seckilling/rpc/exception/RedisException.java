package com.deng.seckilling.rpc.exception;

/**
 * RPC-redis异常类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/4 18:51
 */
public class RedisException extends RuntimeException {

    public RedisException() {
        super();
    }

    public RedisException(String message) {
        super(message);
    }


}
