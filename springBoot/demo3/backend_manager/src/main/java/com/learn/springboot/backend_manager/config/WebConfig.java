package com.learn.springboot.backend_manager.config;

import com.learn.springboot.backend_manager.interceptor.LoginInterceptor;
import com.learn.springboot.backend_manager.interceptor.RedisCounterInterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private RedisCounterInterceptor redisCounterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                // 拦截所有请求，采取白名单模式
                .addPathPatterns("/**")
                // 静态资源, 以及暴露页面为白名单
                .excludePathPatterns("/login", "/", "", "/css/**", "/js/**", "/images/**", "/fonts/**", "/druid/**");

        registry.addInterceptor(redisCounterInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/js/**", "/images/**", "/fonts/**", "/druid/**");
    }
    
    
}
