package com.learnJava.mybatis.mapper;

import com.learnJava.pojo.Dept;
import com.learnJava.pojo.Emp;
import com.learnJava.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class DynamicSQLMapperTest {

    @Test
    public void getEmpByMultipleConditions() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);

        List<Emp> emps = mapper.getEmpByMultipleConditions(
                new Emp(
                        null,
                        "",
                        null,
                        "男",
                        "xx@qq.com",
                        null
                )
        );
        emps.forEach(System.out::println);
    }

    @Test
    public void getEmpByUniqueChoose() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);

        List<Emp> emps = mapper.getEmpByUniqueChoose(
                new Emp(
                        null,
                        "",
                        null,
                        "男",
                        "xx@qq.com",
                        null
                )
        );
        emps.forEach(System.out::println);
    }

    @Test
    public void multipleDeleteByArray() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);

        int res = mapper.multipleDeleteByArray(new Integer[]{3, 5});
        System.out.println(res);
    }

    @Test
    public void multipleInsertByList() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);

        var emps = List.of(
                new Emp(null, "abc", 33, "男", "eee@qq.com", null),
                new Emp(null, "zcc", 23, "女", "rrr@qq.com", null),
                new Emp(null, "etc", 53, "女", "iii@qq.com", null)
        );
        int res = mapper.multipleInsertByList(emps);
        System.out.println(res);
    }
}