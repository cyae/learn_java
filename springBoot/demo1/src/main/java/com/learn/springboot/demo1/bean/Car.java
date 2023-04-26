package com.learn.springboot.demo1.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ConfigurationProperties: 在配置文件中配置的属性, 将会使用setter方法自动注入到该组件创建的对象中
 * 等价于用@Value赋初值  和  xml文件中的<property name="attr" value="value"/>
 */
@ConfigurationProperties(prefix = "audi")

// 需要首先要将Car注册到IOC容器:
// 要么在pojo组件上使用@Component
// 要么在配置类MyConfig上使用@EnableConfigurationProperties 或使用@Import
@Component


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private String brand;
    private Integer price;
}
