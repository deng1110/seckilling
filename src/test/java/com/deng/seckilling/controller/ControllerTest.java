package com.deng.seckilling.controller;

import com.deng.seckilling.domain.UserCookie;
import com.deng.seckilling.rpc.constant.RpcResponse;
import com.deng.seckilling.rpc.redis.RedisClient;
import com.deng.seckilling.rpc.redis.RedisLocker;
import com.deng.seckilling.rpc.util.UUIDUtils;
import com.deng.seckilling.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.concurrent.CountDownLatch;

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

    @Resource
    private GoodsController goodsController;

    @Resource
    private RedisLocker redisLocker;

    @Resource
    private OrderService orderService;

    private static final Integer THREAD_COUNT = 300;

        private static final CountDownLatch ctl = new CountDownLatch(THREAD_COUNT);

    @Test
    public void afterMiaosha() {

        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    orderService.afterMiaoshaService(10016L,16L,UUIDUtils.uuid()+"NEFU",2);
                }
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void miaoshaTest() {
        UserCookie userCookie = new UserCookie();
        userCookie.setId(10016L);
        userCookie.setUserName("xixi2");
        long starttime = System.currentTimeMillis();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    RpcResponse rpcResponse = goodsController.miaosha(userCookie, 16L, 2);
                    log.info("===>code:" + rpcResponse.getCode() + ", message:" + rpcResponse.getMsg() + ", data:" + rpcResponse.getData());
                    ctl.countDown();
                }
            }).start();
        }

        try {
            ctl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("===>执行时间："+(System.currentTimeMillis()-starttime)+"毫秒");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}