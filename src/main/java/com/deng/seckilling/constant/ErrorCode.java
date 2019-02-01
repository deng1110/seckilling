package com.deng.seckilling.constant;

import com.deng.seckilling.rpc.ErrorInfo;

/**
 * ErrorCode常量表
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/1 15:25
 */
public class ErrorCode {

    public static final ErrorInfo SECKILLING_PARAMS_ERROR = new ErrorInfo(1001, "入参不能为空或格式错误");
    public static final ErrorInfo USERLOGIN_FALSE_ERROR = new ErrorInfo(1002, "用户名或者密码错误");
}
