package com.learn.microservice.service_2.service;

import com.learn.microservice.service_2.model.Person;

public interface PersonService {

    Person getPersonById(int id);
    
    Person getPersonByName(String name);
}
