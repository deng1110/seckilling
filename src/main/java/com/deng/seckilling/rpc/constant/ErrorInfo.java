package com.deng.seckilling.rpc.constant;

/**
 * RPC-调用错误信息
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/1/29 11:02
 */
public class ErrorInfo {
    /**
     * 错误编码
     */
    private int code;

    /**
     * 错误提示消息
     */
    private String message;

    public ErrorInfo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorInfo() {
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorInfo{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
