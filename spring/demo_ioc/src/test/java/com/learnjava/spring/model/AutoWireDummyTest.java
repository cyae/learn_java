package com.learnjava.spring.model;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class AutoWireDummyTest {

    @Test
    public void testAutoWireDummy() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("autowire.xml")) {
            AutoWireDummy bean = context.getBean("autowire", AutoWireDummy.class);
            System.out.println(bean);
        }
    }

}