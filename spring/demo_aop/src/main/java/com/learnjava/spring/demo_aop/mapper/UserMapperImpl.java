package com.learnjava.spring.demo_aop.mapper;

public class UserMapperImpl implements UserMapper {

    @Override
    public int add(int a, int b) {
        System.out.println("执行add...");
        return a + b;
    }
    
    @Override
    public UserMapper update(String id) {
        System.out.println("执行update, 将id修改为:" + id);
        return this;
    }
    
}
