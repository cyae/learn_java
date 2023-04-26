package com.learnjava.spring.demo_aop.model;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserTest {
    @Test
    void testAdd() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");) {
            User user = context.getBean("user", User.class);
            user.add();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
