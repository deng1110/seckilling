package com.deng.seckilling.rpc.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * RPC-Redis配置
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/4 16:01
 */
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisConfig {

    private String host;

    private String passWord;

    private Integer port;

    private Integer timeout;//秒

    private Integer poolMaxTotal;

    private Integer poolMaxldle;

    private Integer poolMaxWait;//秒

    public String getHost() {
        return host;
    }

    public String getPassWord() {
        return passWord;
    }

    public Integer getPort() {
        return port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public Integer getPoolMaxTotal() {
        return poolMaxTotal;
    }

    public Integer getPoolMaxldle() {
        return poolMaxldle;
    }

    public Integer getPoolMaxWait() {
        return poolMaxWait;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setPoolMaxTotal(Integer poolMaxTotal) {
        this.poolMaxTotal = poolMaxTotal;
    }

    public void setPoolMaxldle(Integer poolMaxldle) {
        this.poolMaxldle = poolMaxldle;
    }

    public void setPoolMaxWait(Integer poolMaxWait) {
        this.poolMaxWait = poolMaxWait;
    }
}
