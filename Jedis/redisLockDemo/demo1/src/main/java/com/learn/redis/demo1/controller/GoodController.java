package com.learn.redis.demo1.controller;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;

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

    @GetMapping("/buy")
    public String buy() {
        // 单机锁, 这里写成排他锁, 可能死等
        // 可以使用ReentrantLock设置等待过期时间
        // 也可以volatile + CAS
        synchronized (this) {
            ValueOperations<String, String> op = stringRedisTemplate.opsForValue();

            // 分布式锁, 上锁
            String value = UUID.randomUUID().toString() + Thread.currentThread().getName();
            try {
                // 加入超时机制, 防止灾难性的死锁
                // 但引入新问题: 如果某线程业务时间超过超时时间, redis会自动删除锁
                // 导致其他等待线程可以涌入, 类似缓存雪崩, 使用缓存续期机制解决: 每当缓存快过期就加一定时间
                // 而且当其他线程上锁并开始业务后, 假如原线程刚好完成, 则会删除不属于原线程的锁
                Boolean success = op.setIfAbsent(MONITOR, value, 10L, TimeUnit.SECONDS); /* setNX */

                if (!success) {
                    return "购买失败，库存不足，服务器端口：" + serverPort;
                }

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
                // 解锁分布式锁, 能保证多个微服务在正常工作的前提下ACID
                // 但在发生灾难时, 比如宕机, kill -9, 仍会导致释放锁失败
                // 解决思路: 加过期时间
                /*
                if (op.get(MONITOR).equalsIgnoreCase(value)) { // 判断是否是自己线程的锁, 防止误删
                    stringRedisTemplate.delete(MONITOR); // 但if与equals操作没有原子性, 可能会出现误解锁
                }*/

                // 使用lua脚本或事务(乐观锁), 可以保证原子性...
                /* while (true) {
                    stringRedisTemplate.watch(MONITOR);
                    if (op.get(MONITOR).equalsIgnoreCase(value)) {
                        stringRedisTemplate.setEnableTransactionSupport(true);
                        stringRedisTemplate.multi();
                        stringRedisTemplate.delete(MONITOR);
                        List<Object> exec = stringRedisTemplate.exec();
                        if (exec == null) {
                            // 乐观锁被别人抢先, 重试
                            continue;
                        }
                        stringRedisTemplate.unwatch();
                        break;
                    }
                } */

                Jedis jedis = new Jedis();
                String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
                try {
                    Object o = jedis.eval(script, Collections.singletonList(MONITOR), Collections.singletonList(value));
                    if ("1".equals(o.toString())) {
                        System.out.println("success");
                    } else {
                        System.out.println("fail");
                    }
                } finally {
                    if (jedis != null) {
                        jedis.close();
                    }
                }
                // 至此, 多服务的分布式锁已经解决
                // 但对于多个redis集群, 由于redis是AP, 不保证一致性, 当主redis还未完成主从复制就宕机, 从redis立马上位, 在主redis里的锁就会丢失
                // 因此考虑引入CP的zookeeper, 牺牲一定可用性, 统一管理集群
                // 以上步骤已经规范为redlock, Java实现为Redisson
            }
        }
    }

}