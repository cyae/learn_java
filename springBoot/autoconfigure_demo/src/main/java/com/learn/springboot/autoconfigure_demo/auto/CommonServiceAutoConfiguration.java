package com.learn.springboot.autoconfigure_demo.auto;

import com.learn.springboot.autoconfigure_demo.bean.CommonProperties;
import com.learn.springboot.autoconfigure_demo.service.CommonService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * CommonServiceAutoConfiguration
 */
@Configuration
@EnableConfigurationProperties(CommonProperties.class)
public class CommonServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(CommonService.class)
    public CommonService commonService() {
        return new CommonService();
    }
}