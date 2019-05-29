package com.deng.seckilling.aspect;

import com.deng.seckilling.annotation.IsLogin;
import com.deng.seckilling.constant.Rank;
import com.deng.seckilling.domain.User;
import com.deng.seckilling.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录验证的切面
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/29 10:07
 */
@Aspect
@Component
@Slf4j
public class IsLoginAspect {

    @Resource
    private UserService userService;

    @Pointcut("@annotation(com.deng.seckilling.annotation.IsLogin)")
    public void isLogin() {
    }

    /**
     * 登录验证功能，验证失败自动跳到登录页
     */
    @Around("isLogin()")
    public Object isLogin(ProceedingJoinPoint joinPoint) throws Throwable {
        IsLogin isLogin = getIsLogin(joinPoint);
        if (!isLogin.required()) {
            return joinPoint.proceed();
        }
        Object[] params = joinPoint.getArgs();
        for (Object param : params) {
            if (User.class.equals(param.getClass())) {
                User user = (User) param;
                if (null == user.getId()) {
                    //未登录跳转登录界面
                    return isLogin.toUrl();
                }
                if (isLogin.requiredRoot()) {
                    if (!Rank.ADMIN.getValue().equals(user.getRank())) {
                        //不是管理员，尝试非法访问踢下线
                        userService.logOutService();
                        log.error("===>userId:" + user.getId() + ", userName:" + user.getUserName() + "; illegal access, and then compel logout");
                        return isLogin.toUrl();
                    }
                    return joinPoint.proceed();
                } else {
                    return joinPoint.proceed();
                }
            }
        }
        return isLogin.toUrl();
    }

    /**
     * 获取注解实体
     */
    private IsLogin getIsLogin(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod().getAnnotation(IsLogin.class);
    }
}
