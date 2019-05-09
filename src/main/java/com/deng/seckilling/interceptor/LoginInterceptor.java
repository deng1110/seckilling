package com.deng.seckilling.interceptor;

import com.deng.seckilling.constant.DefaultValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/24 19:52
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (DefaultValue.LONGIN_URI_VALUE.equals(request.getRequestURI()) || DefaultValue.REGISTER_URI_VALUE.equals(request.getRequestURI())) {
            return true;
        }
        if (request.getSession().getAttribute(DefaultValue.SESSION_KEY_VALUE) == null) {
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
