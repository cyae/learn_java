package com.learn.demo3.service;

import com.learn.demo3.constant.CacheType;
import com.learn.demo3.model.Book;

/**
 * BookService
 */
public interface BookService {

    Book getBookById(String id, CacheType cacheType);

    boolean updateBook(Book book);
}