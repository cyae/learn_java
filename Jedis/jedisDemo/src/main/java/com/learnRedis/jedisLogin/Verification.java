package com.learnRedis.jedisLogin;

import java.util.Random;

import redis.clients.jedis.Jedis;

/**
 * 模拟手机验证码:
 * 1. 页面内输入手机号, 点击发送后随机生成6位数字验证码, 2分钟内有效
 * 2. 输入验证码, 点击验证, 返回成功或失败
 * 3. 每个手机号每天只能验证3次
 */
public class Verification {
    public static void main(String[] args) {
        verifyCount("13588889999");
        // verifyCode("13588889999", "777");
    }

    public static void verifyCode(String phone, String usrCode) {
        try (Jedis jedis = new Jedis("172.28.162.140", 6379)) {
            String codeKey = "VerifyCode" + phone + ":code";
            String code = jedis.get(codeKey);

            if (code == null) {
                System.out.println("超时!");
            } else {
                if (code.equals(usrCode)) {
                    System.out.println("success");
                } else {
                    System.out.println("fail");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void verifyCount(String phone) {
        try (Jedis jedis = new Jedis("172.28.162.140", 6379)) {
            String countKey = "VerifyCode" + phone + ":count";
            String codeKey = "VerifyCode" + phone + ":code";

            String count = jedis.get(countKey);
            if (count == null) {
                jedis.setex(countKey, 24 * 60 * 60, "1");
            } else if (Integer.parseInt(count) < 3) {
                jedis.incr(countKey);
            } else {
                throw new RuntimeException("发送次数超过3次!");
            }

            String code = codeGenerator();
            jedis.setex(codeKey, 60 * 2, code);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String codeGenerator() {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }

        return sb.toString();
    }
}
