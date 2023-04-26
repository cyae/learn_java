package com.learnjava.spring.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class UserTest {
    @Test
    void testBeanInit() {

        ApplicationContext context1 = new ClassPathXmlApplicationContext("userBean.xml");
        System.out.println("读取xml完毕, 下面开始getBean...");
        User user1 = context1.getBean("user1", User.class);
        System.out.println(user1);

        System.out.println("=========================================");

        ApplicationContext context2 = new FileSystemXmlApplicationContext(
                "E:\\Documents\\JavaProjects\\spring\\demo\\src\\main\\resources\\userBean.xml");
        System.out.println("读取xml完毕, 下面开始getBean...");
        User user2 = context2.getBean("user2", User.class);
        System.out.println(user2);

        System.out.println("=========================================");

        BeanFactory beanFactory = new DefaultListableBeanFactory(context1);
        System.out.println("读取xml完毕, 下面开始getBean...");
        User user3 = beanFactory.getBean("user3", User.class);
        System.out.println(user3);
    }
}
