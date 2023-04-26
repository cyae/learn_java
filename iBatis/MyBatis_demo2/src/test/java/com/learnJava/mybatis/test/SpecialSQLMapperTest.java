package com.learnJava.mybatis.test;

import com.learnJava.mybatis.mapper.SpecialSQLMapper;
import com.learnJava.mybatis.pojo.User;
import com.learnJava.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class SpecialSQLMapperTest {

    @Test
    public void getUserByLike() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);

        List<User> users = mapper.getUserByLike("a");
        users.forEach(System.out::println);
    }

    @Test
    public void deleteUserById() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);

        var res = mapper.deleteUserById("3,4,5");
        System.out.println(res);
    }

    @Test
    public void getUserByTableName() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);

        List<User> users1 = mapper.getUserByTableName("t_user");
        users1.forEach(System.out::println);

        List<User> users2 = mapper.getUserByTableName("t_emp");
        users2.forEach(System.out::println);
    }

    @Test
    public void insertUser() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SpecialSQLMapper mapper = sqlSession.getMapper(SpecialSQLMapper.class);

        User user = new User(null, "jack", "xxx", 12, "男", "tt@qq.com");
        System.out.println(user.getId());
        mapper.insertUser(user);
        System.out.println(user.getId());
    }
}