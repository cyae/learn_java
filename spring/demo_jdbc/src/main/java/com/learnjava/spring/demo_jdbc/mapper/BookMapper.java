package com.learnjava.spring.demo_jdbc.mapper;

import java.util.List;

import com.learnjava.spring.demo_jdbc.model.Book;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


public interface BookMapper {

    void add(@Nullable Book book);

    void deleteBookById(@NonNull String id);

    void update(@NonNull Book book);

    int getTotal();

    Book selectById(String id);

    @Nullable
    List<Book> getAll();

    void batchAdd(List<Object[]> batchArgs);
    
}
