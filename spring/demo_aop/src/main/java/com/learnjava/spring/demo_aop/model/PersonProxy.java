package com.learnjava.spring.demo_aop.model;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class PersonProxy {
    
    @Before("execution(* com.learnjava.spring.demo_aop.model.User.add(..))")
    public void afterReturning() {
        System.out.println("Person before...");
    }
}
