package com.learn.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/scope")
public class ScopeDataSharingController {

    // 跳转到中转页面, 无需任何请求参数以及业务逻辑
    // 则等价于在xml中使用view-controller
    @RequestMapping(value = {"", "/"})
    public String index() {
        return "ScopeDataSharing";
    }

    @RequestMapping("/testServletAPIPOST")
    public String testServletAPI1(HttpServletRequest request) {
        request.setAttribute("name1", "successfully set name via servlet api!");

        // 返回字符串形式的视图名称
        return "ScopeDataSharing";
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView mav = new ModelAndView();

        // 添加数据到ModelAndView
        mav.addObject("name2", "successfully set name via ModelAndView!");

        // 设置视图名称
        mav.setViewName("ScopeDataSharing");
        System.out.println(mav.getClass().getName());
        return mav;
    }

    @RequestMapping("/testModel")
    public String testModel(Model model) {
        model.addAttribute("name3", "successfully set name via Model!");
        System.out.println(model.getClass().getName());
        return "ScopeDataSharing";
    }

    @RequestMapping("/testMap")
    public String testModel(Map<String, Object> map) {
        map.put("name4", "successfully set name via Map!");
        System.out.println(map.getClass().getName());
        return "ScopeDataSharing";
    }

    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap) {
        modelMap.addAttribute("name5", "successfully set name via ModelMap!");
        System.out.println(ModelMap.class.getName());
        return "ScopeDataSharing";
    }


}

