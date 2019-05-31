package com.deng.seckilling.rpc.redis;

import com.deng.seckilling.rpc.exception.RedisException;
import com.deng.seckilling.rpc.util.CheckDataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * RPC-reids客户端
 * (请关闭你的redis所在linux的防火墙)
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/4 18:46
 */
@Component
@Slf4j
public class RedisClient {

    @Autowired
    private JedisPool jedisPool;

    private static final String SUCCESS_STR = "OK";

    private static final int DEFAULT_EXPIRESECONDS = 60 * 60 * 24;

    /**
     * String类型的set,如果key已存在则替换value,并赋24小时过期时间
     *
     * @param key   键
     * @param value 值
     * @return set是否成功
     */
    public boolean set(String key, String value) {
        checkKeyEmpty(key);
        return set(key, value, DEFAULT_EXPIRESECONDS);
    }

    /**
     * String类型的set，当key已存在则替换value，并赋过期时间
     *
     * @param key           键
     * @param value         值
     * @param expireSeconds 过期时间，单位秒
     * @return set是否成功
     */
    public boolean set(String key, String value, int expireSeconds) {
        checkKeyEmpty(key);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return SUCCESS_STR.equals(jedis.setex(key, expireSeconds, value));
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * String类型的set，当key不存在的时候set,并赋24小时过期时间
     *
     * @param key   键
     * @param value 值
     * @return set是否成功
     */
    public boolean setnx(String key, String value) {
        checkKeyEmpty(key);
        return setnx(key, value, DEFAULT_EXPIRESECONDS);
    }

    /**
     * String类型的set，当key不存在的时候set,并赋过期时间
     *
     * @param key           键
     * @param value         值
     * @param expireSeconds 过期时间，单位秒
     * @return set是否成功
     */
    public boolean setnx(String key, String value, int expireSeconds) {
        checkKeyEmpty(key);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return SUCCESS_STR.equals(jedis.set(key, value, "NX", "EX", expireSeconds));
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * String类型的get
     *
     * @param key 键
     * @return 对应的String值
     */
    public String get(String key) {
        checkKeyEmpty(key);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return key是否存在
     */
    public boolean exists(String key) {
        checkKeyEmpty(key);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 删除某个key
     *
     * @param key 键
     * @return 删除是否成功
     */
    public boolean del(String key) {
        checkKeyEmpty(key);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.del(key) > 0;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 给key设置过期时间
     *
     * @param key     键
     * @param seconds 过期时间，单位秒
     * @return 设置过期时间是否成功
     */
    public boolean expire(String key, int seconds) {
        checkKeyEmpty(key);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.expire(key, seconds) > 0;
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 将key中存储的数字值加1
     *
     * @param key 键
     * @return
     */
    public Long incr(String key) {
        checkKeyEmpty(key);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.incr(key);
        } finally {
            returnToPool(jedis);
        }
    }

    public Long decr(String key) {
        checkKeyEmpty(key);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.decr(key);
        } finally {
            returnToPool(jedis);
        }
    }

    /**
     * 校验Redis的key不能为空
     */
    private void checkKeyEmpty(String key) {
        if (CheckDataUtils.isEmpty(key)) {
            throw new RedisException("this key can not be null");
        }
    }

    /**
     * 用完放回Redis连接池
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}