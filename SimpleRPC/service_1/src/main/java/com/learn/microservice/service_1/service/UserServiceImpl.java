package com.learn.microservice.service_1.service;

import org.springframework.stereotype.Service;

import com.learn.microservice.service_1.model.User;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(int id) {
        return new User(id, "default name");
    }

    @Override
    public User getUserByName(String name) {
        return new User(999, name);
    }

    
}
