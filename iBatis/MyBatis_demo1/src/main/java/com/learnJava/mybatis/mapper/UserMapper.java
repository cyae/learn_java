package com.learnJava.mybatis.mapper;

import com.learnJava.mybatis.pojo.User;

/**
 * mybatis使用xxxMapper代替xxxDAO
 * 并且xxxMapper接口无需实现:
 * 1. 可自动定位到xxxMapper.xml文件的sql语句
 * 2. 或使用@Mapper注解
 *
 * Mybatis面向接口编程的两个一致:
 * 1. xxxMapper接口的全类名与xxxMapper.xml的namespace一致
 * 2. xxxMapper接口的方法名与xxxMapper.xml的sql语句id一致
 *
 * @author Rellik
 */
public interface UserMapper {
    /**
     * 添加用户
     * @return 1--成功; 0--失败
     */
    int insertUser();

    /**
     * 修改用户信息
     * @return 1,0
     */
    int updateUser();

    /**
     * 删除用户
     * @return 1,0
     */
    int deleteUser();

    /**
     * 根据id查询用户
     * @return User实例
     */
    User getUserById();
}
