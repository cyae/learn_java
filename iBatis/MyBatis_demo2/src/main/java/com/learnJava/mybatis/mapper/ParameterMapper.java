package com.learnJava.mybatis.mapper;

import com.learnJava.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ParameterMapper {

    User getUserByUserName(String username);

    User checkLoginByDefaultMap(String username, String password);

    User checkLoginByCustomizedMap(Map<String, Object> map);

    User checkLoginByParamAnnotation(@Param("anno_username") String username, @Param("anno_password") String password);

    int insertUser(User user);
}
