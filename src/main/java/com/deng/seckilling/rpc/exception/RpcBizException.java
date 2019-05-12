package com.deng.seckilling.rpc.exception;

import com.deng.seckilling.rpc.constant.ErrorInfo;

/**
 * RPC-用于防御式容错使用异常类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/12 16:36
 */
public class RpcBizException extends RuntimeException {

    private ErrorInfo errorInfo;

    public RpcBizException(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }
}
