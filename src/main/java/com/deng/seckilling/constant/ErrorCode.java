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
    public static final ErrorInfo USERLOGIN_FAIL_ERROR = new ErrorInfo(1002, "用户名或者密码错误");
    public static final ErrorInfo QUERYUSER_NULL_ERROR = new ErrorInfo(1003, "查询成功，但查出零条数据");
    public static final ErrorInfo REGISTER_FAIL_ERROR = new ErrorInfo(1004, "注册失败");
    public static final ErrorInfo USERNAME_EXIT_ERROR = new ErrorInfo(1005, "用户名已存在");
    public static final ErrorInfo USER_NOTEXIT_ERROR = new ErrorInfo(1006,"该ID对应用户不存在");
    public static final ErrorInfo COMPLETE_USERINFO_ERROR = new ErrorInfo(1007,"完善个人信息失败");
    public static final ErrorInfo QUERYUSER_FAIL_ERROR = new ErrorInfo(1008,"查询用户失败");
    public static final ErrorInfo INVALID_USER_ERROR = new ErrorInfo(1009,"作废用户账户失败");
    public static final ErrorInfo FROZEN_USER_ERROR = new ErrorInfo(1010,"冻结用户账户失败");


}
