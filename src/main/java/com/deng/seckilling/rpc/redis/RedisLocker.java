package com.deng.seckilling.rpc.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * RPC-分布式锁
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/11 19:44
 */
@Component
@Slf4j
public class RedisLocker implements Locker {

    @Resource
    private RedisClient redisClient;

    private static final ThreadLocal<Map<String, String>> lockKV = new ThreadLocal<>();

    @Override
    public boolean lock(String key) {
        return this.lock(key, 5);
    }

    @Override
    public boolean lock(String key, int seconds) {
        int waitTime = 20;
        int totalWaitTime = 0;
        int time = seconds * 1000;
        Long timeMillis = System.currentTimeMillis();
        String name = Thread.currentThread().getName();
        String value = name + timeMillis;
        while (totalWaitTime < time && !redisClient.setnx(key, value, seconds)) {
            try {
                Thread.sleep(waitTime);
                totalWaitTime += waitTime;
            } catch (InterruptedException e) {
                //防御性容错
            }
        }
        if (totalWaitTime >= time) {
            throw new RuntimeException("Can not get lock for key \"" + key + "\".");
        }
        Map<String, String> map = new HashMap<String, String>();
        lockKV.set(map);
        lockKV.get().put(key, value);
        return true;
    }

    @Override
    public boolean unlock(String key) {
        if (lockKV.get().containsKey(key)) {
            String value = lockKV.get().get(key);
            String redisValue = redisClient.get(key);
            if (value.equals(redisValue)) {
                if (redisClient.del(key)) {
                    lockKV.get().remove(key);
                    return true;
                }
                log.error("unlock failed for key: {}", key);
                return false;
            }
        }
        return true;
    }

}
