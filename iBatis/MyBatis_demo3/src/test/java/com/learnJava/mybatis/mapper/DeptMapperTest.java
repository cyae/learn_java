package com.learnJava.mybatis.mapper;

import com.learnJava.pojo.Dept;
import com.learnJava.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeptMapperTest {

    @Test
    public void getDeptByEmpDid() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);

        Dept dept = mapper.getDeptByEmpDid(2);
    }

    @Test
    public void getDeptAndEmpByDid() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);

        var dept = mapper.getDeptAndEmpByDid(2);
        dept.getEmps().forEach(System.out::println);

    }
}