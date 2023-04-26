package com.learn.springboot.demo1.controller;

import com.learn.springboot.demo1.bean.Car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RestController = @Controller + @ResponseBody
@RestController
public class HelloController {

    @Autowired
    Car car;

    @RequestMapping("/car")
    public Car car() {
        // 由于👆已经标注了@RestController, 且spring-starter-web中引入了jackson
        // 这里可以直接返回对象, 会被解析为json格式
        // 由RequestMappingHandler负责类型转换
        return car;
    }
    
    @RequestMapping("/hello")
    public String handle01() {
        return "Hello World!";
    }

     
}
