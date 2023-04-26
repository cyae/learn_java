package com.learn.springboot.demo2.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RequestController {
    
    @GetMapping("/goto")
    public String gotoPage(HttpServletRequest request) {
        request.setAttribute("msg", "成功");
        request.setAttribute("code", 200);
        return "forward:/success";
    }

    @GetMapping("/success")
    @ResponseBody
    public Map<?,?> success(
        HttpServletRequest request,
        @RequestAttribute(value = "msg", required = false) String msg,
        @RequestAttribute(value = "code", required = false) Integer code) {
            Object attribute = request.getAttribute("msg");
            Map<String, Object> map = new HashMap<>();
            map.put("req_msg", attribute);
            map.put("annotation_msg", msg);
            return map;
        }

    @GetMapping("/params")
    public String testParam(Map<String, Object> map, Model model, HttpServletRequest request, HttpServletResponse response) {
        
        model.addAttribute("msg", "成功 from model");
        map.put("msg", "成功 from map");
        
        // 这里request设置的"msg"在跳转到/success页面后, 
        // 会在success()方法的业务逻辑执行完, 渲染视图后(model的请求域暴露阶段), 被model中存储的"msg"覆盖
        // 因此最终页面只会显示model.addAttribute与map.put中的后者保存的"msg"
        request.setAttribute("msg", "成功 from request");

        response.addCookie(new Cookie("c1", "v1"));

        return "forward:/success";
    }
}
