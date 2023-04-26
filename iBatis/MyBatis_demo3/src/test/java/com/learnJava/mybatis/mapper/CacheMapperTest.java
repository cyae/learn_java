package com.learnJava.mybatis.mapper;

import com.learnJava.pojo.Emp;
import com.learnJava.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class CacheMapperTest {

    @Test
    public void sessionSelect() {
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);

        Emp emp1 = mapper.SessionSelect(4);
        System.out.println(emp1);



        System.out.println("mybatis一级缓存工作在session层面, 第二次相同条件的查询从缓存读取, 无需再次执行sql");
        Emp emp2 = mapper.SessionSelect(4);
        System.out.println(emp2);

    //    一级缓存失效的情况:
    //    1. 查询间开启并使用新session
    //    2. 条件不同
        Emp emp3 = mapper.SessionSelect(5);
    //    3. 查询间有增删改, 即使未造成实质影响
    //    4. 查询间手动清除一级缓存
        sqlSession.clearCache();
    }

    @Test
    public void sessionFactorySelect() {
        SqlSession sqlSession1 = null;
        SqlSession sqlSession2 = null;
        CacheMapper mapper1 = null;
        CacheMapper mapper2 = null;
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
            sqlSession1 = sqlSessionFactory.openSession(true);
            sqlSession2 = sqlSessionFactory.openSession(true);
            mapper1 = sqlSession1.getMapper(CacheMapper.class);
            mapper2 = sqlSession2.getMapper(CacheMapper.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Emp emp1 = mapper1.SessionSelect(4);
        System.out.println(emp1);
        // 提交关闭以使得结果刷入二级缓存
        sqlSession1.commit();
        sqlSession1.close();

        Emp emp2 = mapper2.SessionSelect(4);
        System.out.println(emp2);

        //    二级缓存失效的情况:
        //    1. 查询间有增删改, 即使未造成实质影响

    //    Mybatis缓存查询顺序:
    //    1. 先二级后一级最后数据库
    //    2. session关闭后, 一级缓存会刷入二级缓存
    }
}