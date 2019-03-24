package com.deng.seckilling.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/2 0:28
 */
@Component
@Aspect
@Slf4j
public class LogAop {

//    @Before("execution(* com.deng.seckilling.controller.UserController.*(..))")
//    public void beforeLog(JoinPoint joinPoint){
//        log.info("我是AOP的before的log,此次的方法是："+joinPoint.getSignature().getName()+"begin with "+Arrays.toString(joinPoint.getArgs()));
//    }
}
