package com.deng.seckilling.rpc.Listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/10 16:17
 */
@Component
@Slf4j
public class StartListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("======= Your project is ready. Technical support by deng-rpc ======");
    }
}
