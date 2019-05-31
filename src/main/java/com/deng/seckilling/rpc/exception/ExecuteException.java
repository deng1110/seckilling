package com.deng.seckilling.rpc.exception;

/**
 * 用于项目级别执行异常使用
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/31 11:06
 */
public class ExecuteException extends RuntimeException {
    public ExecuteException() {
        super();
    }
    public ExecuteException(String message) {
        super(message);
    }

    public ExecuteException(String message, Throwable cause) {
        super(message, cause);
    }
}
