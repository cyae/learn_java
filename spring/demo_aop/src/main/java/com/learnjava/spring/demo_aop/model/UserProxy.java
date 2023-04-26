package com.learnjava.spring.demo_aop.model;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect // 生成增强代理对象
@Order(2) // 规定增强代理类的优先级
public class UserProxy {

    // 用于抽取相同的切入点
    @Pointcut("execution(* com.learnjava.spring.demo_aop.model.User.add(..))")
    public void pointCut() {}

    // @Before("execution(* com.learnjava.spring.demo_aop.model.User.add(..))")
    @Before("pointCut")
    public void before() {
        System.out.println("before logic");
    }

    // @After("execution(* com.learnjava.spring.demo_aop.model.User.add(..))")
    @After("pointCut")
    public void after() {
        System.out.println("after logic");
    }

    @AfterReturning("execution(* com.learnjava.spring.demo_aop.model.User.add(..))")
    public void afterReturning() {
        System.out.println("afterReturning logic");
    }

    @AfterThrowing("execution(* com.learnjava.spring.demo_aop.model.User.add(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing logic");
    }

    @Around("execution(* com.learnjava.spring.demo_aop.model.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("aroundUp logic");

        proceedingJoinPoint.proceed();

        System.out.println("aroundDown logic");
    }

}
