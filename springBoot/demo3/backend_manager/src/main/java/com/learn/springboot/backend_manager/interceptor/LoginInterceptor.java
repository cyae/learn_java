package com.learn.springboot.backend_manager.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Profile("dev")
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
        log.info("拦截了:" + request.getRequestURI());
        // 登陆检查
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        if (user != null) {
            request.setAttribute("msg", "欢迎回来");
            log.info("放行了:" + request.getRequestURI());
            return true;
        }
        request.setAttribute("msg", "请先登陆");
        request.getRequestDispatcher("login").forward(request, response);
        log.info("未放行:" + request.getRequestURI());
        return false;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
    ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
