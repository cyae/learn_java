package com.learn.springboot.backend_manager.Service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.springboot.backend_manager.bean.UserForSQL;

// 对于单表CRUD, Service层利用现有的UserMapper在ServiceImpl类中实现了大量方法
// 这些方法的规范在IService接口中
public interface UserService extends IService<UserForSQL> {

    // 多表CRUD方法...
}
