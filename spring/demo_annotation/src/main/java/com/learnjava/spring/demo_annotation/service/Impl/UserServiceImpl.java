package com.learnjava.spring.demo_annotation.service.Impl;

import com.learnjava.spring.demo_annotation.mapper.Interface.UserMapper;
import com.learnjava.spring.demo_annotation.service.Interface.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

// 等价于<bean id="userService" class="..."/>
// @Service(value = "userService")

// 若不写value, 则默认id=驼峰类名
@Service
public class UserServiceImpl implements UserService {

    // 以往的依赖注入：
    // @Override
    // public void registerUser() {
    //     try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml")) {
    //         UserMapper userMapper = context.getBean("userMapperImpl", UserMapper.class);
    //         userMapper.insert();
    //         System.out.println("user registered...");
    //     }
    // }

    // 等价于<property/>
    @Value("init uuid")
    private String UUID;

    // 等价于内部bean，外部bean注入
    @Autowired
    @Qualifier("userMapperImpl")
    private UserMapper userMapper;

    @Resource
    private UserMapper userMapper1;

    @Resource(name = "userMapperImpl")
    private UserMapper userMapper2;

    @Override
    public void registerUser() {
        userMapper.insert();
        System.out.println(UUID);
        System.out.println("user registered...");
    }
}
