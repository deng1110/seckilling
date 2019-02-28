package com.deng.seckilling.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/2/28 14:20
 */
@Slf4j
@Component
public class BaseProducer implements RabbitTemplate.ConfirmCallback {

    //由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    private static RabbitTemplate rabbitTemplate;

    /**
     * 构造方法注入rabbitTemplate
     */
    @Autowired
    public BaseProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this); //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
    }

    /**
     * 生产者发送消息的方法
     *
     * @param exchangeName 交换机名称
     * @param routingKey   绑定key
     * @param content      待发送的消息内容
     */
    public void sendMsg(String exchangeName, String routingKey, String content) {
        //生成一个发送者发送的时候用的消息Id
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        //把消息放入rountingkey对应的队列当中去，
        rabbitTemplate.convertAndSend(exchangeName, routingKey, content, correlationId);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (!ack) {
            log.warn("消息发送失败:" + cause);
        }
    }
}
