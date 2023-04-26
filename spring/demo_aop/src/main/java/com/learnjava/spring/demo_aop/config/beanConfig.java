package com.learnjava.spring.demo_aop.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"com.learnjava.spring"})
// 开启aop支持, 等于xml里的<aop:aspectj-autoproxy></aop:aspectj-autoproxy>
// proxyTargetClass默认为false, 表示使用jdk代理模式
// true表示使用cglib代理模式
@EnableAspectJAutoProxy(proxyTargetClass = true) 
public class beanConfig {
    
}
