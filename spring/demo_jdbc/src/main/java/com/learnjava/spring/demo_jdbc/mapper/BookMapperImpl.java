package com.learnjava.spring.demo_jdbc.mapper;

import java.util.Arrays;
import java.util.List;

import com.learnjava.spring.demo_jdbc.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookMapperImpl implements BookMapper {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(Book book) {
        String sql = "insert into t_user values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, book.getId(), book.getUsername(), book.getPassword(), book.getAge(), book.getSex(), book.getEmail());
    }

    @Override
    public void deleteBookById(String id) {
        String sql = "delete from t_user where id=?";
        int res = jdbcTemplate.update(sql, id);

        System.out.println(res);
    }

    @Override
    public void update(Book book) {
        String sql = "update t_user set id=?,username=?,password=?,age=?,sex=?,email=?";
        int res = jdbcTemplate.update(sql, book.getId(), book.getUsername(), book.getPassword(), book.getAge(), book.getSex(),
                book.getEmail());

        System.out.println(res);
    }

    @Override
    public int getTotal() {
        String sql = "select count(*) from t_user";
        Integer res = jdbcTemplate.queryForObject(sql, Integer.class);
        return res;
    }

    @Override
    public Book selectById(String id) {
        String sql = "select * from t_user where id=?";
        // 第二个参数见jdbc的DBUtil的beanHandler, 反射+泛型实现
        Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Book>(Book.class), id);
        return book;
    }

    @Override
    public List<Book> getAll() {
        String sql = "select * from t_user";
        List<Book> books = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Book>(Book.class));
        return books;
    }

    @Override
    public void batchAdd(List<Object[]> batchArgs) {
        String sql = "insert into t_user values (?,?,?,?,?,?)";
        int[] res = jdbcTemplate.batchUpdate(sql, batchArgs);
        Arrays.stream(res).forEach(System.out::println);
    }

    
}
