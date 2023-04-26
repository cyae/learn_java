package com.JNF.stream_functional;

import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        UserService userService = new UserService();
        List<UserDTO> users = userService.getAllUsers();
        Utils.printUser(users);

        System.out.println("************************");
        
        UserService2 userService2 = new UserService2();
        users = userService2.getAllUsers();
        Utils.printUser(users);
    }

    public void printUser(Collection<User> users) {
        users.forEach(System.out::println);
    }
}
