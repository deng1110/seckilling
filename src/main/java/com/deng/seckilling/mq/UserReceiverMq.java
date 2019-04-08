//package com.deng.seckilling.mq;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.lang.management.ManagementFactory;
//
///**
// * @author: dengjunbing
// * @version: v1.0
// * @since: 2019/2/27 18:09
// */
//@Component
//@Slf4j
//public class UserReceiverMq {
//
//    String me = "当前进程：" + ManagementFactory.getRuntimeMXBean().getName() + ";当前线程：" + Thread.currentThread().getId() + Thread.currentThread().getName();
//
//    @RabbitListener(queues = "userConsumer")
//    @RabbitHandler
//    public void process(String content) {
//        log.info(me + "消费者接受到的消息为： " + content);
//    }
//
//}
