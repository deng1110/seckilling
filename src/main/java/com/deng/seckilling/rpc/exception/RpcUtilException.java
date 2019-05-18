package com.deng.seckilling.rpc.exception;

/**
 * RPC-工具异常类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/4 20:48
 */
public class RpcUtilException extends RuntimeException {

    public RpcUtilException() {
        super();
    }

    public RpcUtilException(String message) {
        super(message);
    }

    public RpcUtilException(Throwable e) {
        super(e);
    }

    public RpcUtilException(String message, Throwable e) {
        super(message, e);
    }
}
