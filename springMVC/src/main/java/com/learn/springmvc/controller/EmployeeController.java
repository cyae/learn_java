package com.learn.springmvc.controller;

import com.learn.springmvc.mapper.EmployeeMapperImpl;
import com.learn.springmvc.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequestMapping("/employee_home")
public class EmployeeController {

    @Autowired
    private EmployeeMapperImpl employeeMapper;

    @RequestMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/employee")
    public ModelAndView getAllEmployee() {
        Collection<Employee> employees = employeeMapper.getAll();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("employees", employees);
        return mav;
    }

    // 由于直到html5以前, form不支持delete请求, 所以需要用post包装瞒天过海
    @DeleteMapping("/employee/{id}")
    // @PathVariable用于获取解析url后表单中的{id}, 并将其赋值给形参id
    public String deleteEmployee(@PathVariable("id") Integer id) {
        employeeMapper.deleteById(id);
        return "redirect:/employee_home/employee";
    }

    @PostMapping("/employee/add")
    public String addEmployee(Employee employee) {
        employeeMapper.save(employee);
        return "redirect:/employee_home/employee";
    }

    @GetMapping("/employee/{id}")
    public String getEmployeeById(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeMapper.getById(id);
        model.addAttribute("employee", employee);
        return "index";
    }

    @PutMapping("/employee")
    public String updateEmployee(Employee employee) {
        employeeMapper.save(employee);
        return "redirect:/employee_home/employee";
    }
}
