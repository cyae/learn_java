package com.learnjava.spring.model;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class LifeCycleTest {
    @Test
    public void testLifeCycle() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("lifeCycleBean.xml");

        LifeCycle bean = context.getBean("lifeCycle", LifeCycle.class);
        System.out.println(bean);

        context.close();
    }
}