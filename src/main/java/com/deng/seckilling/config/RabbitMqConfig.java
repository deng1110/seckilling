//package com.deng.seckilling.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.beans.factory.annotation.Value;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
///**
// * RabbitMq配置类
// *
// * @author: dengjunbing
// * @version: v1.0
// * @since: 2019/2/28 14:56
// */
//@Configuration
//@Slf4j
//@Component
//public class RabbitMqConfig {
//
//    //一些常量
//    private final static String USERCONSUMER_QUEUENAME_VALUE = "userConsumer";
//    private final static String USERCONSUMER2_QUEUENAME_VALUE = "userConsumer2";
//    private final static String DIRECT_EXCHANGENAME_VALUE = "sendUser.direct";
//    private final static String DIRECT_ROUTINGKEY_VALUE = "sendUserRoutingKey";
//
//    //读取配置文件的一些值
//    @Value("${deng.rabbitmq.addresses}")
//    private String host;
//    @Value("${deng.rabbitmq.port}")
//    private int port;
//    @Value("${deng.rabbitmq.username}")
//    private String username;
//    @Value("${deng.rabbitmq.password}")
//    private String password;
//    @Value("${deng.rabbitmq.virtual}")
//    private String virtual;
//
//    /**
//     * 配置连接工厂的方法
//     *
//     * @return ConnectionFactory实例
//     */
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
//        connectionFactory.setUsername(username);
//        connectionFactory.setPassword(password);
//        connectionFactory.setVirtualHost(virtual);
//        connectionFactory.setPublisherConfirms(true);
//        return connectionFactory;
//    }
//
//    /**
//     * 配置RabbitMq的模板
//     *
//     * @return RabbitTemplate的实例
//     */
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    //必须是prototype类型
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        return template;
//    }
//
//
//    //----------------------下面是设置配置队列(方法名尽量和队列名一致)----------------------
//    @Bean
//    public Queue userConsumer() {
//        return new Queue(USERCONSUMER_QUEUENAME_VALUE, true); //队列持久
//    }
//    @Bean
//    public Queue userConsumer2() {
//        return new Queue(USERCONSUMER2_QUEUENAME_VALUE, true); //队列持久
//    }
//
//
//    //----------------------下面是设置配置绑定----------------------
//    @Bean
//    public Binding binding() {
//        return BindingBuilder.bind(userConsumer()).to(new DirectExchange(DIRECT_EXCHANGENAME_VALUE, true, false)).with(DIRECT_ROUTINGKEY_VALUE);
//    }
//    @Bean
//    public Binding binding2() {
//        return BindingBuilder.bind(userConsumer2()).to(new DirectExchange(DIRECT_EXCHANGENAME_VALUE, true, false)).with(DIRECT_ROUTINGKEY_VALUE);
//    }
//
//
////    @Bean
////    public SimpleMessageListenerContainer simpleMessageListenerContainer_one() {
////        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory());
////        simpleMessageListenerContainer.addQueues(userConsumer());
////        simpleMessageListenerContainer.setExposeListenerChannel(true);
////        simpleMessageListenerContainer.setMaxConcurrentConsumers(5);
////        simpleMessageListenerContainer.setConcurrentConsumers(5);
////        simpleMessageListenerContainer.setPrefetchCount(5);
////        simpleMessageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL); //设置确认模式手工确认
////        return simpleMessageListenerContainer;
////    }
//
//
//}
