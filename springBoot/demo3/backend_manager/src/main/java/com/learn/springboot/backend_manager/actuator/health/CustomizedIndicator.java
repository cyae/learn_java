package com.learn.springboot.backend_manager.actuator.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health.Builder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class CustomizedIndicator extends AbstractHealthIndicator {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    protected void doHealthCheck(Builder builder) throws Exception {
        redisTemplate.opsForValue().set("test", "test");
        if (redisTemplate.opsForValue().get("test").equals("test")) {
            builder.up().withDetail("message", "CustomizedIndicator is up");
        } else {
            builder.down().withDetail("message", "CustomizedIndicator is down");
        }
    }
    
}
