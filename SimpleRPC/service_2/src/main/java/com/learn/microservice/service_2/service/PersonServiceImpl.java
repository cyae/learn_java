package com.learn.microservice.service_2.service;

import org.springframework.stereotype.Service;

import com.learn.microservice.service_2.model.Person;

@Service
public class PersonServiceImpl implements PersonService {

    @Override
    public Person getPersonById(int id) {
        return new Person(id, "default name", 100);
    }

    @Override
    public Person getPersonByName(String name) {
        return new Person(999, name, 100);
    }
    
}
