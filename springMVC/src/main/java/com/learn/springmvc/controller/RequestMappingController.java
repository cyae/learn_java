package com.learn.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RequestMapping注解用于指定浏览器request到controller方法的映射路径, 必须修饰controller类和/或方法
//映射路径应与html中的超链接路径href一致
@RequestMapping("/requestMapping") // 表示此controller中所有方法, 首先映射到host:port/artifact/home
@Controller
public class RequestMappingController {

    @RequestMapping(value = {
            "/",
            ""
    }) // 再映射到host:port/artifact/home/和host:port/artifact/home
    public String index() {
        // 返回值指定的是逻辑视图名，经thymeleaf解析, 在返回值字符串"RequestMapping"加上:
        // 视图前缀"/WEB-INF/templates/"    视图后缀".html"
        // 得到完整的资源路径"/WEB-INF/templates/RequestMapping.html"
        return "RequestMapping";
    }

    @RequestMapping(value = "/success",method = {RequestMethod.POST, RequestMethod.GET}) // 再映射到host:port/artifact/home/success
    public String target() {
        return "success";
    }

    @RequestMapping("/success/{id}") // 再映射到.../success?id=id, 相当于rest风格的/success/id
    public String testPathVariable(@PathVariable("id") Integer id) {
        System.out.println("id = " + id);
        return "success";
    }
}
