package com.learn.springboot.backend_manager.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.springboot.backend_manager.Service.UserService;
import com.learn.springboot.backend_manager.bean.UserForSQL;
import com.learn.springboot.backend_manager.exception.UserTooManyException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * TableController
 */
@Controller
public class TableController {

    @GetMapping("/basic_table")
    public String basic_table() {
        return "table/basic_table";
    }

    @Autowired
    UserService userService;
    
    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn", defaultValue = "1") Integer pn, 
                                Model model) {
        // 表格内容遍历
        // List<UserForSQL> users = userService.list();
        Page<UserForSQL> page = new Page<>(pn, 5);
        Page<UserForSQL> users = userService.page(page);
        if (users.getPages() > 100) {
            throw new UserTooManyException("用户数量超过100个");
        }

        model.addAttribute("users", users);
        return "table/dynamic_table";
    }

    @GetMapping("/user/delete/{pn}/{id}")
    public String deleteUser(@PathVariable("id") Integer id, @PathVariable("pn") Integer pn, RedirectAttributes ra) {
        userService.removeById(id);
        ra.addAttribute("pn", pn);
        return "redirect:/dynamic_table";
    }

    @GetMapping("/responsive_table")
    public String responsive_table() {
        return "table/responsive_table";
    }

    @GetMapping("/editable_table")
    public String editable_table() {
        return "table/editable_table";
    }
}