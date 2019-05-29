package com.deng.seckilling.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 验证是否登录
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/29 9:54
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsLogin {

    boolean required() default true;

    /**
     * 登录验证失败后跳转到的界面
     */
    String toUrl() default "login";

    boolean requiredRoot() default false;

}
