package com.learn.microservice.service_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

// import com.learn.microservice.service_1.service.UserService;
// import com.learn.microservice.service_1.model.User;
import com.learn.microservice.service_2.model.Person;
import com.learn.microservice.service_2.rpc.Stub;
import com.learn.microservice.service_2.service.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {
    
    public static void start() throws UnknownHostException, IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.valueOf(br.readLine());
        String name = br.readLine();

        // UserService userService = (UserService) Stub.getStub(UserService.class);
        // User user1 = userService.getUserById(id);
        // log.info(user1.toString());
        // User user2 = userService.getUserByName(name);
        // log.info(user2.toString());
        
        PersonService personService = (PersonService) Stub.getStub(PersonService.class);
        Person person1 = personService.getPersonById(id);
        log.info(person1.toString());
        Person person2 = personService.getPersonByName(name);
        log.info(person2.toString());

    }
}
