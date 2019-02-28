package com.deng.seckilling.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/27 18:08
 */
@Component
@Slf4j
public class UserSenderMq extends BaseProducer {

    /**
     * 构造方法注入rabbitTemplate
     *
     * @param rabbitTemplate
     */
    public UserSenderMq(RabbitTemplate rabbitTemplate) {
        super(rabbitTemplate);
    }

    /**
     * 生产者发送消息
     *
     * @param exchangeName 交换机名称
     * @param routingKey   绑定key
     * @param content      待发送的消息内容
     */
    public void sendMsg(String exchangeName, String routingKey, String content) {
        for (int i = 0; i < 100; i++) {
            String str = "" + i;
            super.sendMsg(exchangeName, routingKey, str);
        }

    }

}
