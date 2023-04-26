package com.learnJava.mybatis.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.learnJava.mybatis.pojo.Emp;
import com.learnJava.mybatis.pojo.EmpExample;
import com.learnJava.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EmpMapperTest {

    @Test
    public void selectByExample() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        //查询所有, 相当于无条件
        List<Emp> empList = mapper.selectByExample(null);
        empList.forEach(System.out::println);

    }

    @Test
    public void selectByExample2() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        //条件查询, QBC风格query by create
        EmpExample empExample = new EmpExample();
        empExample.createCriteria().andAgeBetween(10, 50).andEmailIsNotNull();
        empExample.or().andDidIsNull();

        List<Emp> empList1 = mapper.selectByExample(empExample);
        empList1.forEach(System.out::println);

    }

    @Test
    public void updateByPrimaryKey() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        // 如果QBC中属性为null, 则使用"null"覆盖原记录
        int res = mapper.updateByPrimaryKey(new Emp(1, "admin", 22, "女", "sss@qq.com", 2));
        Emp emp = mapper.selectByPrimaryKey(1);
        System.out.println(emp);

        // Selective表示如果QBC中属性为null, 则跳过覆盖
        int res2 = mapper.updateByPrimaryKeySelective(new Emp(1, "admin", 22, null, "sss@qq.com", 2));
        Emp emp1 = mapper.selectByPrimaryKey(1);
        System.out.println(emp1);
    }

    @Test
    public void testSelectByExample() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

        try (Page<Emp> objects = PageHelper.startPage(4, 3)) {
            System.out.println("详细信息:" + objects);

            List<Emp> empList = mapper.selectByExample(null);
            empList.forEach(System.out::println);

            PageInfo<Emp> pageInfo = new PageInfo<>(empList, 5);
            System.out.println("导航分页信息:" + pageInfo);

        }

    }
}