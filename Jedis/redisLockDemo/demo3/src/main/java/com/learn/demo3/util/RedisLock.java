package com.learn.demo3.util;

import java.util.Collections;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learn.demo3.constant.REDIS_KEY_CONST;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

@Component
public class RedisLock {

    private static JedisPool jedisPool;

    @Autowired
    public void setJedisPool(JedisPool jedisPool) {
        RedisLock.jedisPool = jedisPool;
    }

    /**
     * 锁健
     */
    private final static String KEY = REDIS_KEY_CONST.LOCK_KEY;

    /**
     * 锁过期时间
     */
    private final static long LOCK_EXPIRED = 3000;

    /**
     * 锁竞争超时时间
     */
    private final static long LOCK_WAIT_TIME_OUT = 999999;

    /**
     * SET命令参数
     */
    static SetParams params = SetParams.setParams().nx().px(LOCK_EXPIRED);

    /**
     * ThreadLocal用于保存某个线程共享变量：对于同一个static ThreadLocal
     * 不同的线程只能从中get，set到自己线程的副本
     */
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 尝试获取锁
     * 
     * @param key
     * @return
     */
    public Boolean tryLock(String key) {
        String value = UUID.randomUUID().toString();
        Jedis resource = jedisPool.getResource();
        long startTime = System.currentTimeMillis();
        try {
            for (;;) {
                // SET命令返回OK，获取锁成功
                String set = resource.set(KEY.concat(key), value, params);
                if ("OK".equals(set)) {
                    threadLocal.set(value);
                    return true;
                }
                // 增加一个超时时间判断
                if (System.currentTimeMillis() - startTime > LOCK_WAIT_TIME_OUT) {
                    return false;
                }
                // 休眠一段时间 递归调用
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            resource.close();
        }
    }

    /**
     * 释放锁 通过lua脚本实现
     * 
     * @param key
     * @return
     */
    public boolean unLock(String key) {
        Jedis resource = null;
        try {
            resource = jedisPool.getResource();
            String script = "if redis.call('get', KEYS[1]) == ARGV[1] then" +
                    " return redis.call('del', KEYS[1]) " +
                    "else" +
                    " return 0 " +
                    "end";
            Object eval = resource.eval(script, Collections.singletonList(KEY.concat(key)),
                    Collections.singletonList(threadLocal.get()));
            if ("1".equals(eval.toString())) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (resource != null) {
                resource.close();
            }
        }
    }
}
