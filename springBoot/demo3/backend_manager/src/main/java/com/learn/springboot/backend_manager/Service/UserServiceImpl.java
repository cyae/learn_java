package com.learn.springboot.backend_manager.Service;


import java.io.Serializable;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.springboot.backend_manager.bean.UserForSQL;
import com.learn.springboot.backend_manager.mapper.UserMapper;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
@Transactional
// 对于单表CRUD, Service层利用现有的UserMapper在ServiceImpl类中实现了大量方法
public class UserServiceImpl extends ServiceImpl<UserMapper, UserForSQL> implements UserService {
    
    private Counter counter;

    // 定制化监控service操作
    public UserServiceImpl(MeterRegistry registry) {
        counter = registry.counter("op1");
    }
    @Override
    public UserForSQL getById(Serializable id) {
        counter.increment();
        return super.getById(id);
    }
    
    // 多表CRUD方法实现...
}
