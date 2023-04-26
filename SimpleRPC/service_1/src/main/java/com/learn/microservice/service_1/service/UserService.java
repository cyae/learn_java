package com.learn.microservice.service_1.service;

import com.learn.microservice.service_1.model.User;

public interface UserService {
    
    User getUserById(int id);

    User getUserByName(String name);
}
