package com.deng.seckilling.constant;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/2 15:12
 */
public class DefaultValue {

    /**
     * 入参的枚举常量类型的值，供CheckDataUtils方法中检测枚举类型调用
     */
    public static final String SEX_VALUE_MALE = "male";
    public static final String SEX_VALUE_FEMALE = "female";
    public static final Integer FENYE_PAGESIZE_VALUE = 5;

    /**
     * 拦截器中所用URI
     */
    public static final String LONGIN_URI_VALUE = "/user/login";
    public static final String REGISTER_URI_VALUE = "/user/register";

    /**
     * 获取session中数据
     */
    public static final String SESSION_KEY_VALUE = "sessionId";
    public static final Long SESSION_VALUE_VALUE = 10000L;
}
