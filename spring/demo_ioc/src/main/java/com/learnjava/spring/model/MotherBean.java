package com.learnjava.spring.model;

import org.springframework.beans.factory.FactoryBean;

public class MotherBean implements FactoryBean<User> {

    @Override
    public User getObject() throws Exception {
        //决定返回类型
        User user = new User();
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
