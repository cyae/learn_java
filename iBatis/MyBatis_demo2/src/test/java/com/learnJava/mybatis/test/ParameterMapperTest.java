package com.learnJava.mybatis.test;

import com.learnJava.mybatis.mapper.ParameterMapper;
import com.learnJava.mybatis.pojo.User;
import com.learnJava.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ParameterMapperTest {

    @Test
    public void testGetUserByUsername() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);

        User user = mapper.getUserByUserName("张三");
        System.out.println(user);
    }

    @Test
    public void checkLoginByDefaultMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);

        User user = mapper.checkLoginByDefaultMap("张三", "123456");
        System.out.println(user);
    }

    @Test
    public void checkLoginByCustomizedMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);

        // 自定义哈希表传递参数
        Map<String, Object> map = new HashMap<>();
        map.put("username", "张三");
        map.put("password", "123456");

        User user = mapper.checkLoginByCustomizedMap(map);
        System.out.println(user);

    }

    @Test
    public void insertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);

        int res = mapper.insertUser(new User(null, "李四", "123", 23, "女", "xxx@qq.com"));
        System.out.println(res);
    }

    @Test
    public void checkLoginByParamAnnotation() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);

        User user = mapper.checkLoginByParamAnnotation("张三", "123456");
        System.out.println(user);
    }
}
