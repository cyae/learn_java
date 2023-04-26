package com.learn.springboot.backend_manager.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    
    // 根据底层源码, 如果标注了@ExceptionHandler, 
    // 则会在处理异常遍历exceptionResolver时取到本方法
    // 方法需要返回一个ModelAndView, 也可以跳转到其他页面的ModelAndView
    @ExceptionHandler(Exception.class)
    public String handle(Exception ex) {
        log.info("异常捕获: " + ex);
        return "login";
    }
}
