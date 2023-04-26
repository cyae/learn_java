package com.learnjava.spring.demo_jdbc.service;

import java.util.List;

import com.learnjava.spring.demo_jdbc.mapper.BookMapper;
import com.learnjava.spring.demo_jdbc.model.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(
    propagation = Propagation.REQUIRED,
    isolation = Isolation.DEFAULT,
    timeout = 60,
    readOnly = false,
    rollbackFor = NullPointerException.class,
    noRollbackForClassName = "ArithmeticException"
) // 在service层声明事务注解, 内部所有方法都ACID
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public void addBook(Book book) {
        bookMapper.add(book);
    }

    // 为某一具体业务方法声明事务注解
    @Transactional(propagation = Propagation.SUPPORTS)
    public void updateBook(Book book) {
        bookMapper.update(book);
    }

    public void deleteBookById(String id) {
        bookMapper.deleteBookById(id);
    }

    public int getTotel() {
        return bookMapper.getTotal();
    }

    public Book selectById(String id) {
        return bookMapper.selectById(id);
    }

    public List<Book> getAll() {
        return bookMapper.getAll();
    }

    public void batchAdd(List<Object[]> batchArgs) {
        bookMapper.batchAdd(batchArgs);
    }
}
