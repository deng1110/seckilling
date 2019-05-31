package com.deng.seckilling.config;

import com.deng.seckilling.domain.User;
import com.deng.seckilling.domain.UserCookie;
import com.deng.seckilling.service.UserService;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;

/**
 * 增加ArgumentResolver
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/27 11:03
 */
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Resource
    private UserService userService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class clzz = parameter.getParameterType();
        return clzz == UserCookie.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        UserCookie userCookie = userService.getUserFromRequest();
        return userCookie == null ? new User() : userCookie;
    }
}
