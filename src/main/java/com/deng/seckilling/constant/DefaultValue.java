package com.deng.seckilling.constant;

import com.deng.seckilling.rpc.util.Md5Utils;
import com.deng.seckilling.rpc.util.UUIDUtils;

/**
 * 默认值类
 *
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
     * 分页中的首页
     */
    public static final Integer FENYE_FIRSTPAGE_VALUE = 1;

    /**
     * 拦截器中所用URI
     */
    public static final String LONGIN_URI_VALUE = "/user/login";
    public static final String REGISTER_URI_VALUE = "/user/register";

    /**
     * cookie名
     */
    public static final String COOKIE_NAME = "seckilling_cookie_name";

    /**
     * cookie过期时间,无操作三小时，cookie失效
     */
    public static final int COOKIE_EXPIRE_TIME = 60 * 60 * 3;

    /**
     * 超级管理员的用户的ID
     */
    public static final Long SESSION_ROOT_VALUE = 10000L;

    /**
     * redis库存缓存后缀
     */
    public static final String STOCK_SUFFIX_VALUE = "_STOCK";

    /**
     * 分布式锁前缀
     */
    public static final String LOCKER_PREFIX_VALUE = "MIAOSHA_LOCKER_";
}
