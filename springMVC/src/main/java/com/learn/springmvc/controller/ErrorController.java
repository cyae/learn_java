package com.learn.springmvc.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/")
    public String error() {
        return "testError";
    }

    @RequestMapping("/testError")
    public String testError() {
        // 发生异常, 要么被xml配置的异常处理器捕获, 要么被下面的注解异常处理器捕获
        System.out.println(10 / 0);
        return "testError";
    }

    // 基于注解的springMVC异常处理
    @ExceptionHandler(ArithmeticException.class)
    public String handleArithmeticException(ArithmeticException ex, Model model) {
        // 将异常信息存入视图, 方便在页面中显示
        model.addAttribute("exception", ex);
        return "error/exception";
    }
}
