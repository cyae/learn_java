package com.learn.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class RESTUserController {

    @RequestMapping(value = {""})
    public String index() {
        return "RESTUser";
    }

    // @RequestMapping(value = {""}, method = RequestMethod.GET)
    @GetMapping(value = {""})
    public String getAllUsers() {
        return "RESTUser";
    }

    // @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    @GetMapping(value = {"/{id}"})
    public String getUserById() {
        return "RESTUser";
    }

    // @RequestMapping(value = {""}, method = RequestMethod.POST)
    @PostMapping(value = {""})
    public String createUser(String username, String password) {
        System.out.println("POST");
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        return "RESTUser";
    }

    // @RequestMapping(value = {""}, method = RequestMethod.PUT)
    @PutMapping(value = {""})
    public String updateUser(String username, String password) {
        System.out.println("PUT");
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        return "RESTUser";
    }

}
