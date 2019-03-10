package com.deng.seckilling.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/1 18:32
 */
@Slf4j
public class SessionUtils<T> {

    public static <T> String setSession(HttpServletRequest request, T t) {
        HttpSession session = request.getSession(true);
        session.setAttribute(session.getId(), t);
        log.info("存储SessionID为" + session.getId() + ";数据为" + t);
        return session.getId();
    }

    public static <T extends Object> T getSession(HttpServletRequest request, String sessionId) {
        HttpSession session = request.getSession(false);
        if (null == session) {
            log.error("没有任何可获取的Session");
            return (T) "no Session";
        }
        log.info("获取SessionID为"+sessionId+";数据为"+session.getAttribute(sessionId));
        return (T) session.getAttribute(sessionId);
    }
}
