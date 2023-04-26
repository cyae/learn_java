package com.learnjava.spring.demo_annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.learnjava.spring.demo_annotation"})
public class BeanConfig {
}
