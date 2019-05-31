package com.deng.seckilling.controller;

import com.deng.seckilling.rpc.redis.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/5/27 9:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ControllerTest {

    @Resource
    private RedisClient redisClient;

    @Test
    public void test() {
        log.info(redisClient.set("key1","value1")+"");
        log.info(redisClient.set("key2","20")+"");
        log.info(redisClient.get("key1"));
        log.info(redisClient.get("key2"));
//        log.info(redisClient.incr("key1")+"");
        log.info(redisClient.incr("key2")+"");
//        log.info(redisClient.decr("key1")+"");
        log.info(redisClient.decr("key2")+"");
        log.info(""+redisClient.setnx("key1","value111"));
        log.info(""+redisClient.get("key1"));
    }
}