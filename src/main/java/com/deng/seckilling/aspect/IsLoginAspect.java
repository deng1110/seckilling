package com.deng.seckilling.aspect;

import com.deng.seckilling.annotation.IsLogin;
import com.deng.seckilling.constant.ErrorCode;
import com.deng.seckilling.constant.Rank;
import com.deng.seckilling.domain.UserCookie;
import com.deng.seckilling.rpc.constant.ErrorInfo;
import com.deng.seckilling.rpc.constant.RpcResponse;
import com.deng.seckilling.rpc.util.CheckDataUtils;
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
        UserCookie userCookie = userService.getUserFromRequest();
        if (!isLogin.required()) {
            return joinPoint.proceed();
        }
        if (isLogin.requiredController()) {
            return forController(joinPoint, isLogin, userCookie);
        } else {
            return forHtml(joinPoint, isLogin, userCookie);
        }
    }

    /**
     * 业务处理类的controller验证
     */
    public Object forController(ProceedingJoinPoint joinPoint, IsLogin isLogin, UserCookie userCookie) throws Throwable {
        if (CheckDataUtils.isEmpty(userCookie) || null == userCookie.getId()) {
            //未登录返回错误信息
            return forControllerReturn(isLogin);
        }
        if (isLogin.requiredRoot()) {
            if (!Rank.ADMIN.getValue().equals(userCookie.getRank())) {
                //不是管理员，尝试非法访问踢下线，返回错误信息
                userService.logOutService(userCookie.getToken());
                log.error("===>userId:" + userCookie.getId() + ", userName:" + userCookie.getUserName() + "; illegal access, and then compel logout");
                return forControllerReturn(isLogin);
            } else {
                return joinPoint.proceed();
            }
        }
        return joinPoint.proceed();
    }


    /**
     * 页面跳转类的controller验证
     */
    public Object forHtml(ProceedingJoinPoint joinPoint, IsLogin isLogin, UserCookie userCookie) throws Throwable {
        if (CheckDataUtils.isEmpty(userCookie) || null == userCookie.getId()) {
            //未登录跳转指定界面
            return isLogin.toUrl();
        }
        if (isLogin.requiredRoot()) {
            if (!Rank.ADMIN.getValue().equals(userCookie.getRank())) {
                //不是管理员，尝试非法访问踢下线，跳转指定界面
                userService.logOutService(userCookie.getToken());
                log.error("===>userId:" + userCookie.getId() + ", userName:" + userCookie.getUserName() + "; illegal access, and then compel logout");
                return isLogin.toUrl();
            } else {
                return joinPoint.proceed();
            }
        }
        return joinPoint.proceed();
    }

    /**
     * controller的返回信息
     */
    public RpcResponse forControllerReturn(IsLogin isLogin) {
        if (CheckDataUtils.isEmpty(isLogin.errorMessage())) {
            return RpcResponse.error(ErrorCode.NOT_LOGIN_ERROR);
        } else {
            return RpcResponse.error(new ErrorInfo(ErrorCode.NOT_LOGIN_ERROR.getCode(), isLogin.errorMessage()));
        }
    }

    /**
     * 获取注解实体
     */
    private IsLogin getIsLogin(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod().getAnnotation(IsLogin.class);
    }
}
