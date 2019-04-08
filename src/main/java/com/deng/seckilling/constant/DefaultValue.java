package com.deng.seckilling.constant;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/2 15:12
 */
public class DefaultValue {

    /**
     * 性别枚举值
     */
    public static final String SEX_VALUE_MALE = "male";
    public static final String SEX_VALUE_FEMALE = "female";

    /**
     * 分页中的每页数据条数默认值
     */
    public static final Integer FENYE_PAGESIZE_VALUE = 10;

    /**
     * 拦截器中所用URI
     */
    public static final String LONGIN_URI_VALUE = "/user/login";
    public static final String REGISTER_URI_VALUE = "/user/register";

    /**
     * 获取session中数据的key
     */
    public static final String SESSION_KEY_VALUE = "sessionId";

    /**
     * 超级管理员的用户的ID
     */
    public static final Long SESSION_VALUE_VALUE = 10000L;
}
