package com.learn.springboot.demo2.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @GetMapping("/user")
    public String getUser() {
        return "GET";
    }

    @PostMapping("/user")
    public String postUser() {
        return "POST";
    }

    @PutMapping("/user")
    public String putUser() {
        return "PUT";
    }

    @DeleteMapping("/user")
    public String deleteUser() {
        return "DELETE";
    }
}
