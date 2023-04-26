package com.learn.springboot.backend_manager.actuator.info;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.boot.actuate.info.InfoContributor;

public class AppInfo implements InfoContributor {

    @Override
    public void contribute(Builder builder) {
        builder.withDetail("message", "相关信息")
                .withDetail("1", "2");
    }
    
}
