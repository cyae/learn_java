package com.learnjava.spring.mapper;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.PooledConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class OrderMapperImpl implements OrderMapper {

    @Override
    public void insert() throws InterruptedException {
        System.out.println("mapper insert called...");
        System.out.println("using SQL to implement...");

        TimeUnit.SECONDS.sleep(1);
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("druidBean.xml")) {
            DruidDataSource source = context.getBean("druid", DruidDataSource.class);
            PooledConnection connection = source.getPooledConnection();
            Connection conn = connection.getConnection();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Done! Quit mapper...");
    }
}
