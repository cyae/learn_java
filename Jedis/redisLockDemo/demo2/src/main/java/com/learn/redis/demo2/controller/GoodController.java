package com.learn.redis.demo2.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodController {

    public static final String MONITOR = "分布式锁";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private Redisson redisson;

    @GetMapping("/buy")
    public String buy() {
        // 单机锁, 这里写成排他锁, 可能死等
        // 可以使用ReentrantLock设置等待过期时间
        // 也可以volatile + CAS
        ValueOperations<String, String> op = stringRedisTemplate.opsForValue();

        // 分布式锁, 上锁
        RLock lock = redisson.getLock(MONITOR);
        lock.lock();

        try {
            String res = op.get("goods:001");
            Integer goodNumber = Integer.valueOf(res);

            if (goodNumber > 0) {
                goodNumber--;
                op.set("goods:001", goodNumber.toString());
                return "购买成功，剩余库存：" + goodNumber + "，服务器端口：" + serverPort;
            } else {
                return "购买失败，库存不足，服务器端口：" + serverPort;
            }
        } finally {
            if (lock.isLocked()) {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }
    }

}