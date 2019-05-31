package com.deng.seckilling.rpc.rabbitmq;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * RPC-RabbitMq配置
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/26 8:41
 */
@Component
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitMqConfig {

    private String host;

    private String userName;

    private String passWord;

    private Integer port;

    private String virtual;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getVirtual() {
        return virtual;
    }

    public void setVirtual(String virtual) {
        this.virtual = virtual;
    }
}
