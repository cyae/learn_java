package com.learnJava.mybatis.test;

import com.learnJava.mybatis.mapper.SelectMapper;
import com.learnJava.mybatis.pojo.User;
import com.learnJava.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class SelectMapperTest {

    @Test
    public void testSelectUserByUserId() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        User user = mapper.selectUserByUserId(14);
        System.out.println(user);
    }

    @Test
    public void testGetAllUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        var res = mapper.getAllUser();
        res.forEach(System.out::println);
    }

    @Test
    public void getCount() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        var count = mapper.getCount();
        System.out.println(count);
    }

    @Test
    public void getUserByUserIdToMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        var res = mapper.getUserByUserIdToMap(13);
        res.entrySet().forEach(System.out::println);
    }

    @Test
    public void getAllUserToMap() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);

        var res = mapper.getAllUserToMap();
        res.entrySet().forEach(System.out::println);
    }
}