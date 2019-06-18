//package com.deng.seckilling.rpc.rabbitmq;
//
//import com.deng.seckilling.rpc.context.RpcApplicationContext;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.util.concurrent.TimeoutException;
//
///**
// * @author: dengjunbing
// * @version: v1.0
// * @since: 2019/5/26 15:35
// */
//@Configuration
//@Slf4j
//public class RabbitMqBean {
//
//    @Resource
//    private RpcApplicationContext rpcApplicationContext;
//
//    @Bean
//    public Connection getConnection() {
//        ConnectionFactory connectionFactory = new ConnectionFactory();
//        connectionFactory.setHost(rabbitMqConfig.getHost());
//        connectionFactory.setUsername(rabbitMqConfig.getUserName());
//        connectionFactory.setPassword(rabbitMqConfig.getPassWord());
//        connectionFactory.setPort(rabbitMqConfig.getPort());
//        connectionFactory.setVirtualHost(rabbitMqConfig.getVirtual());
//        RabbitTemplate
//        try {
//            return connectionFactory.newConnection();
//        } catch (IOException e) {
//            log.error("RabbitMq create Connection fail, cause:{}", e);
//            return null;
//        } catch (TimeoutException e) {
//            log.error("RabbitMq create Connection fail, cause:{}", e);
//            return null;
//        }
//    }
//
//}
