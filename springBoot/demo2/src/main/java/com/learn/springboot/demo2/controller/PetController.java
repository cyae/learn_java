package com.learn.springboot.demo2.controller;

import com.learn.springboot.demo2.bean.Pet;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PetController {
    
    @RequestMapping("/pet")
    public Pet getPet(Pet pet) {
        return pet;
    }
}
