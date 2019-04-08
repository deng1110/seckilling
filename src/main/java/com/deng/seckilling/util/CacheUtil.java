package com.deng.seckilling.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: dengjunbing
 * @version: v1.0
 * @since: 2019/3/29 14:29
 */
public class CacheUtil<K, V> {

    private final ConcurrentHashMap<K, V> cacheMap = new ConcurrentHashMap<K, V>();

    public Object getCache(K keyValue, String ThreadName) {
        System.out.println("ThreadName getCache==============" + ThreadName);
        Object value = null;
//从缓存获取数据
        value = cacheMap.get(keyValue);
//如果没有的话，把数据放到缓存
        if (value == null) {
            return putCache(keyValue, ThreadName);
        }
        return value;
    }

    public Object putCache(K keyValue, String ThreadName) {
        System.out.println("ThreadName 执行业务数据并返回处理结果的数据（访问数据库等）==============" + ThreadName);
//可以根据业务从数据库获取等取得数据,这边就模拟已经获取数据了
        @SuppressWarnings("unchecked")
        V value = (V) "dataValue";
//把数据放到缓存
        cacheMap.put(keyValue, value);
        return value;
    }
}
