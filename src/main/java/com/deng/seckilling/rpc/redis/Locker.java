package com.deng.seckilling.rpc.redis;

/**
 * RPC-锁接口
 *
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/6/11 19:45
 */
public interface Locker {

    /**
     * 锁定
     * @param key   cache的key
     * @return  是否成功
     */
    boolean lock(String key);

    /**
     * 锁定
     * @param key   cache的key
     * @param seconds   锁定的时长
     * @return  是否成功
     */
    boolean lock(String key, int seconds);

    /**
     * 解除锁
     * @param key   cache的key
     * @return  是否成功
     */
    boolean unlock(String key);
}
