package com.learn.springboot.backend_manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.learn.springboot.autoconfigure_demo.service.CommonService;
import com.learn.springboot.backend_manager.Service.UserService;
import com.learn.springboot.backend_manager.bean.User;
import com.learn.springboot.backend_manager.bean.UserForSQL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    
    @GetMapping(value = {"/", "/login", ""})
    public String login() {
        return "login";
    }

    // 这样写主页跳转会导致登陆成功后, 每刷新一次页面, 都进行一次表单post
    // @PostMapping("/login")
    // public String index(String username, String password) {
    //     return "index.html";
    // }

    
    // 因此单独写一个get请求跳转, 登陆成功后即重定向get页面
    // 防止登陆成功后的刷新重复post表单
    // 同时, 为了防止用户直接访问host:port/index.html, 在session中存入"user"
    @PostMapping("/login")
    public String index(User user, HttpSession session, Model model) {
        if (StringUtils.hasLength(user.getUsername()) && StringUtils.hasLength(user.getPassword())) {
            if (Math.random() > 0.1) { // 校验逻辑...
                session.setAttribute("user", user);
                model.addAttribute("msg", "登陆成功");
                return "redirect:index.html";
            }
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }
        model.addAttribute("msg", "用户名或密码不能为空");
        return "login";
    }
    
    @Autowired
    StringRedisTemplate redisTemplate;
    
    // 之后的所有操作, 都需要先校验session中是否存入过"user"
    @GetMapping("/index.html")
    public String index(HttpServletRequest request, HttpSession session, Model model) {
        // 这最好使用filter或interceptor来实现, 此处在方法内使用get做简易判断
        // Object user = session.getAttribute("user");
        // if (user != null) {
        //     model.addAttribute("msg", "欢迎回来");
        //     return "index";
        // }
        // model.addAttribute("msg", "请先登陆");
        model.addAttribute("msg", (String) request.getAttribute("msg"));
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String index = ops.get("/index.html");
        String sql = ops.get("/sql");
        String login = ops.get("/login");
        String error = ops.get("/error");
        model.addAttribute("index", index);
        model.addAttribute("sql", sql);
        model.addAttribute("login", login);
        model.addAttribute("error", error);
        return "index";
    }

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @GetMapping("/sql")
    @ResponseBody
    public String queryFromDB() {
        Long count = jdbcTemplate.queryForObject("select count(*) from t_user", Long.class);
		return count.toString();
    }

    @Autowired
    UserService userService;

    @GetMapping("/user")
    @ResponseBody
    public UserForSQL selectById(@RequestParam("id") int id) {
        return userService.getById(id);
    }

    @Autowired
    CommonService commonService = new CommonService();
    @GetMapping("/common")
    @ResponseBody
    public String common() {
        String commonMethod = commonService.commonMethod("xxx");
        return commonMethod;
    }
}
