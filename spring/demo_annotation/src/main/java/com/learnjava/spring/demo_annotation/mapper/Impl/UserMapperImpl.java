package com.learnjava.spring.demo_annotation.mapper.Impl;

import com.learnjava.spring.demo_annotation.mapper.Interface.UserMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserMapperImpl implements UserMapper {
    @Override
    public void insert() {
        System.out.println("inserted...");
    }

    @Override
    public void updateById() {
        System.out.println("updated...");
    }

    @Override
    public void deleteById() {
        System.out.println("deleted...");
    }
}
