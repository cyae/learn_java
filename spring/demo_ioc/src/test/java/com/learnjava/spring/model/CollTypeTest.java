package com.learnjava.spring.model;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class CollTypeTest {
    @Test
    public void testCollType() {
        ApplicationContext context = new ClassPathXmlApplicationContext("collBean.xml");
        CollType bean = context.getBean("coll", CollType.class);
        System.out.println(bean);
    }
}