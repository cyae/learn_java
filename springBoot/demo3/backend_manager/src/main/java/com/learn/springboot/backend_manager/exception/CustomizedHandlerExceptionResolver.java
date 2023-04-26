package com.learn.springboot.backend_manager.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

// 让自定义异常解析器的优先级最高
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
public class CustomizedHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {

        try {
            response.sendError(555, "my error");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView();
    }
    
}
