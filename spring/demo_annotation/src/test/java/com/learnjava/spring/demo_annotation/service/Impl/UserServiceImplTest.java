package com.learnjava.spring.demo_annotation.service.Impl;

import com.learnjava.spring.demo_annotation.config.BeanConfig;
import com.learnjava.spring.demo_annotation.service.Interface.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    @Test
    void registerUserWithXML() {
        try (ClassPathXmlApplicationContext context
                     = new ClassPathXmlApplicationContext("bean.xml")) {
            UserService userService = context.getBean("userServiceImpl", UserService.class);
            userService.registerUser();
        }
    }

    @Test
    void registerUserWithAnnotation() {
        try (AnnotationConfigApplicationContext context
                     = new AnnotationConfigApplicationContext(BeanConfig.class)) {
            UserService userService = context.getBean("userServiceImpl", UserService.class);
            userService.registerUser();
        }
    }
}