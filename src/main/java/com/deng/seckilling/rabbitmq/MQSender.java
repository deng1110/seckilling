package com.deng.seckilling.rabbitmq;

import com.deng.seckilling.rpc.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * mq的发送
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/12 19:36
 */
@Service
@Slf4j
public class MQSender {


    @Autowired
    AmqpTemplate amqpTemplate;

    /**
     * 直接发mq到队列
     *
     * @param mm 发送mq的消息实体
     */
    public void sendMiaoshaMessage(MessageDTO mm) {
        amqpTemplate.convertAndSend(MqConfig.MIAOSHA_QUEUE, JsonUtils.toJson(mm));
        log.info("===>mq sender message:{}", mm.toString());
    }

}
