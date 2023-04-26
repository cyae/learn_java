package com.learnJava.mybatis.mapper;

import com.learnJava.pojo.Emp;
import com.learnJava.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EmpMapperTest {

    @Test
    public void getAllEmp() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        List<Emp> emps = mapper.getAllEmp();
        emps.forEach(System.out::println);
    }

    @Test
    public void getEmpAndDept() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        Emp emp = mapper.getEmpAndDept(4);
        System.out.println(emp);
    }

    @Test
    public void getEmpAndDeptByStep() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        Emp emp = mapper.getEmpAndDeptByStep(4);
        // System.out.println(emp);

        // 延迟加载, 只会执行emp的查询, dept被延迟
        System.out.println(emp.getEmpName());
    }
}