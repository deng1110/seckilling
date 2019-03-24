//package com.deng.seckilling.util;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.Closeable;
//import java.io.IOException;
//
///**
// * RabbitMq工具类
// *
// * @author: dengjunbing
// * @version: v1.0
// * @since: 2019/2/27 18:23
// */
//@Slf4j
//public class RabbitMqUtils {
//
//    //声明一些实例
//    private static ConnectionFactory factory = new ConnectionFactory();
//    private static Connection connection = null;
//    private static Channel channel = null;
//
//    //一些常量默认值
//    private final static String RABBITMQHOST = "localhost";
//    private final static int RABBITMQPORT = 5672;
//    private final static String RABBITMQUSERNAME = "guest";
//    private final static String RABBITMQPASSWORD = "guest";
//
//
//    /**
//     * 创建一个Connection,切记：需要手动关闭连接
//     *
//     * @return Connection实例
//     * @throws Exception
//     */
//    public static Connection createConnection() throws Exception {
//        return connection = factory.newConnection();
//    }
//
//    /**
//     * 创建一个Channel，无需手动关闭连接
//     *
//     * @param connection 连接实例
//     * @param vHost      RabbitMq的vhost
//     * @return Channel实例
//     * @throws Exception
//     */
//    public static Channel createChannel(Connection connection, String vHost) throws Exception {
//        return createChannel(connection, vHost, RABBITMQHOST);
//    }
//
//    /**
//     * 创建一个Channel，无需手动关闭连接
//     *
//     * @param connection   连接实例
//     * @param vHost        RabbitMq的vhost
//     * @param rabbitMqHost RabbitMq的地址
//     * @return Channel实例
//     * @throws Exception
//     */
//    public static Channel createChannel(Connection connection, String vHost, String rabbitMqHost) throws Exception {
//        return createChannel(connection, vHost, rabbitMqHost, RABBITMQPORT);
//    }
//
//
//    /**
//     * 创建一个Channel，无需手动关闭连接
//     *
//     * @param connection   连接实例
//     * @param vHost        RabbitMq的vhost
//     * @param rabbitMqHost RabbitMq的地址
//     * @param rabbitMqPort RabbitMq的端口号
//     * @return Channel实例
//     * @throws Exception
//     */
//    public static Channel createChannel(Connection connection, String vHost, String rabbitMqHost, int rabbitMqPort) throws Exception {
//        return createChannel(connection, vHost, rabbitMqHost, rabbitMqPort, RABBITMQUSERNAME, RABBITMQPASSWORD);
//    }
//
//    /**
//     * 创建一个Channel，无需手动关闭连接
//     *
//     * @param connection       连接实例
//     * @param vHost            RabbitMq的vhost
//     * @param rabbitMqUserName RabbitMq的用户名
//     * @param rabbitMqPassWord RabbitMq的密码
//     * @return Channel实例
//     * @throws Exception
//     */
//    public static Channel createChannel(Connection connection, String vHost, String rabbitMqUserName, String rabbitMqPassWord) throws Exception {
//        return createChannel(connection, vHost, RABBITMQHOST, rabbitMqUserName, rabbitMqPassWord);
//    }
//
//    /**
//     * 创建一个Channel，无需手动关闭连接
//     *
//     * @param connection       连接实例
//     * @param vHost            RabbitMq的vhost
//     * @param rabbitMqHost     RabbitMq的地址
//     * @param rabbitMqUserName RabbitMq的用户名
//     * @param rabbitMqPassWord RabbitMq的密码
//     * @return Channel实例
//     * @throws Exception
//     */
//    public static Channel createChannel(Connection connection, String vHost, String rabbitMqHost, String rabbitMqUserName, String rabbitMqPassWord) throws Exception {
//        return createChannel(connection, vHost, rabbitMqHost, RABBITMQPORT, rabbitMqUserName, rabbitMqPassWord);
//    }
//
//    /**
//     * 创建一个Channel，无需手动关闭连接
//     *
//     * @param connection       连接实例
//     * @param vHost            RabbitMq的vhost
//     * @param rabbitMqHost     RabbitMq的地址
//     * @param rabbitMqPort     RabbitMq的端口号
//     * @param rabbitMqUserName RabbitMq的用户名
//     * @param rabbitMqPassWord RabbitMq的密码
//     * @return Channel实例
//     * @throws Exception
//     */
//    public static Channel createChannel(Connection connection, String vHost, String rabbitMqHost, int rabbitMqPort, String rabbitMqUserName, String rabbitMqPassWord) throws Exception {
//        factory.setHost(rabbitMqHost);
//        factory.setPort(rabbitMqPort); //默认端口号
//        factory.setUsername(rabbitMqUserName);//默认用户名
//        factory.setPassword(rabbitMqPassWord);//默认密码
//        factory.setVirtualHost(vHost);
//        channel = connection.createChannel();
//        return channel;
//    }
//
//    /**
//     * 关闭连接
//     *
//     * @throws Exception
//     */
//    public static void close(Closeable... args) {
//        if (args == null) {
//            return;
//        }
//        for (Closeable arg : args) {
//            if (arg != null) {
//                try {
//                    arg.close();
//                } catch (IOException e) {
//                    log.error("关闭连接异常，异常信息为" + e);
//                }
//            }
//        }
//
//    }
//}
