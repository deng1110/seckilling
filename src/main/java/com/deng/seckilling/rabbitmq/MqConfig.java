package com.deng.seckilling.rabbitmq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mq的配置Bean
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/12 21:24
 */
@Configuration
public class MqConfig {

    /**
     * mq的秒杀队列
     */
    public static final String MIAOSHA_QUEUE = "miaosha.queue";

    /**
     * Direct模式 交换机Exchange
     * */
    @Bean
    public Queue queue() {
        return new Queue(MIAOSHA_QUEUE, true);
    }
}
