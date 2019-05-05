package com.deng.seckilling.rpc.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * RPC-redis声明Bean类
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/4 17:55
 */
@Configuration
@Slf4j
public class RedisBean {

    @Autowired
    private RedisConfig redisConfig;

    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisConfig.getPoolMaxldle());
        jedisPoolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig, redisConfig.getHost(), redisConfig.getPort(), redisConfig.getTimeout() * 1000, redisConfig.getPassWord(), 0);
        log.info("Initializing JedisPool; host:{},prot:{},maxTotal:{}", redisConfig.getHost(), redisConfig.getPort(), redisConfig.getPoolMaxTotal());
        return jedisPool;
    }

}
