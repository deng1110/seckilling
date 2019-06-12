package com.deng.seckilling.rabbitmq;

import com.deng.seckilling.rpc.util.JsonUtils;
import com.deng.seckilling.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * mq的接收
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/12 19:36
 */
@Service
@Slf4j
public class MQReceiver {

    @Resource
    private OrderService orderService;

    /**
     * 直接从队列中取消息
     *
     * @param message 待取消息
     */
    @RabbitListener(queues = MqConfig.MIAOSHA_QUEUE)
    public void receive(String message) {
        MessageDTO mm = JsonUtils.toObject(message, MessageDTO.class);
        log.info("===>mq receive message:{}",mm.toString());
        Long userId = mm.getUserId();
        Long skuId = mm.getSkuId();
        String orderSecret = mm.getOrderSecret();
        Integer number = mm.getNumber();
        orderService.afterMiaosha(userId, skuId, orderSecret, number);
    }

}
