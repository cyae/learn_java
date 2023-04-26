package com.learnjava.spring.model;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class MotherBeanTest {

    @Test
    public void testFactoryBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("motherBean.xml");
        User user1 = context.getBean("motherBean1", User.class);
        User user2 = context.getBean("motherBean1", User.class);
        System.out.println(user1 == user2);

        User user3 = context.getBean("motherBean2", User.class);
        User user4 = context.getBean("motherBean2", User.class);
        System.out.println(user3 == user4);
    }
}