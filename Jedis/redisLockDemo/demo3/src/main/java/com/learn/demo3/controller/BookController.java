package com.learn.demo3.controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.demo3.constant.CacheType;
import com.learn.demo3.constant.RETURN_CODE;
import com.learn.demo3.model.Book;
import com.learn.demo3.response.Resp;
import com.learn.demo3.service.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public Resp getBookById(@PathVariable("id") String id) {
        Book book = bookService.getBookById(id, CacheType.SIDE);
        Resp resp = new Resp();
        if (book != null) {
            resp.setRetCode(RETURN_CODE.RET_OK)
                .setRetMsg("OK");
            return resp;
        }
        resp.setRetCode(RETURN_CODE.RET_WRONG).setRetMsg("WRONG!");
        return resp;
    }

    @PostMapping("/{id}")
    public Resp updateBook() {
        Resp resp = new Resp();
        Book book = new Book(12, "book-" + UUID.randomUUID().toString().substring(5), new BigDecimal("100.1"));
        if (bookService.updateBook(book)) {
            return resp.setRetCode(RETURN_CODE.RET_OK).setRetMsg("OK");
        }
        resp.setRetCode(RETURN_CODE.RET_WRONG).setRetMsg("WRONG!");
        return resp;
    }
}
