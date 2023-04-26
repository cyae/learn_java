package com.learnRedis.jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class testJedisTest {
    @Test
    public void testTestJedis1() {
        try (Jedis jedis = new Jedis("172.18.208.218", 6379)) {
            jedis.set("uname", "jack");
            String uname = null;
            if (jedis.exists("uname")) {
                uname = jedis.get("uname");
            }
            System.out.println(uname);
            jedis.expire("uname", 20);
            Set<String> keys = jedis.keys("*");
            keys.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTestJedis2() {
        try (Jedis jedis = new Jedis("172.18.208.218", 6379)) {
            jedis.mset("uid", "1", "uname", "jack", "upwd", "123456", "uage", "13");
            List<String> mget = null;
            if (jedis.exists("uid")) {
                mget = jedis.mget("uid", "uname", "upwd", "uage");
            }

            mget.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTestJedis3() {
        try (Jedis jedis = new Jedis("172.18.208.218", 6379)) {
            jedis.lpush("listKey", "l1", "l2", "l3");
            jedis.rpush("listKey", "r1", "r2", "r3");

            List<String> lrange = jedis.lrange("listKey", 0, -1);
            lrange.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTestJedis4() {
        try (Jedis jedis = new Jedis("172.18.208.218", 6379)) {

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("username", "张三");
            map.put("sex", "男");
            map.put("age", "13");

            jedis.hset("hash1", map);

            List<String> hmget = jedis.hmget("hash1", "sex");
            hmget.forEach(System.out::println);

            jedis.flushDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
