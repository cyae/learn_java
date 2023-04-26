package com.learnjava.spring.demo_aop.model;

import org.springframework.stereotype.Component;

@Component
public class User {
    
    public void add() {
        System.out.println("naive add...");
    }
}
