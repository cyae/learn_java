package com.learn.springmvc.controller;

import com.learn.springmvc.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@RequestMapping("/param")
public class ParamController {

    @RequestMapping("/")
    public String index() {
        return "param";
    }

    @RequestMapping("/paramViaServletAPI")
    public String param(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        return "param";
    }

    //同名请求hobby, 使用数组获取
    @RequestMapping("/paramViaControllerArgs")
    public String param(String name, Integer age, String[] hobby) {
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println(Arrays.toString(hobby));
        return "param";
    }

    @RequestMapping("/paramViaRequestParam")
    public String param(
            @RequestParam(value = "name", defaultValue = "nan") String name,
            @RequestParam(value = "age", required = false, defaultValue = "nan") String age) {
        System.out.println("name: " + name);
        System.out.println("age: " + age);
        return "param";
    }

    @RequestMapping("/paramViaRequestHeader")
    public String param(
            @RequestHeader(value = "host", defaultValue = "192.168.0.1:0") String host) {
        System.out.println("host:port " + host);
        return "param";
    }

    @RequestMapping("/paramViaCookieValue")
    public String param(@CookieValue(value = "JSESSIONID", required = false, defaultValue = "nan") String cookieValue, HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println("cookie value: " + cookieValue);
        return "param";
    }

    @RequestMapping("/paramViaPOJO")
    public String param(User user) {
        System.out.println(user);
        return "param";
    }

}
