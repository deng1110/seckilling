package com.deng.seckilling.rpc.aspect;

import com.deng.seckilling.rpc.constant.ErrorInfo;
import com.deng.seckilling.rpc.constant.RpcResponse;
import com.deng.seckilling.rpc.exception.RpcBizException;
import com.deng.seckilling.rpc.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * RPC-防御式容错
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/12 16:33
 */
@Component
@Aspect
@Slf4j
@Order(0)
public class RpcAspect {

    /**
     * 用于统一处理Rest接口异常的Around(环绕)类型切面
     *
     * @param pjp 切面处理入口
     * @return Rest接口方法调用返回结果
     */
    @Around(value = "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public Object around(ProceedingJoinPoint pjp) {
        String className = "";
        String methodName = "";
        try {
            try {
                Signature signature = pjp.getSignature();
                MethodSignature methodSignature = (MethodSignature) signature;
                Method method = methodSignature.getMethod();
                methodName = method.getName();
                className = methodSignature.getDeclaringTypeName();
            } catch (Exception e) {
                //防御性容错
            }
            Object result = pjp.proceed();
            return result;
        } catch (RpcBizException e) {
            log.error("Catch a RpcBizException, code:{}, message:{}.", e.getErrorInfo().getCode(), e.getErrorInfo().getMessage());
            return RpcResponse.error(e.getErrorInfo());
        } catch (Throwable throwable) {
            log.error("RPC called error, class:" + className + ", method:" + methodName + ", params:" + JsonUtils.toJson(pjp.getArgs()), throwable);
            return RpcResponse.error(new ErrorInfo(-1, "系统性错误(RPC invoke error)：" + throwable.getMessage()));
        }
    }

}
