package com.learnjava.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class OrderServiceImplTest {

    @Test
    void complexInsert() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("orderBean.xml")) {
            OrderService orderService = context.getBean("orderService", OrderService.class);
            orderService.complexInsert();
        } catch (InterruptedException e) {
            throw new RuntimeException("SQL错误!");
        } catch (BeanCreationException e) {
            throw new RuntimeException("Bean 创建错误!");
        }
    }
}