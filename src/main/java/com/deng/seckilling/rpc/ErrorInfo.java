package com.deng.seckilling.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * RPC调用错误信息
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/1/29 11:02
 */
@Data
@AllArgsConstructor
public class ErrorInfo {
    /**
     * 错误编码
     */
    private int code;

    /**
     * 错误提示消息
     */
    private String message;
}
