package com.learnjava.spring.demo_jdbc.mapper;

import java.util.List;

import com.learnjava.spring.demo_jdbc.config.TxConfig;
import com.learnjava.spring.demo_jdbc.model.Book;
import com.learnjava.spring.demo_jdbc.service.BookService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:bean.xml")

// 复合式注解, 等价于上述两注解
// @SpringJUnitConfig(locations = "classpath:bean.xml")
public class BookMapperImplTest {

    // 注入式测试, 不用再写context, getBean等重复语句
    @Autowired
    private BookService bookService;

    @Test
    void testAdd() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml")) {
            BookService bookService = context.getBean("bookService", BookService.class);
            bookService.addBook(new Book(null, "asdfasd", "cxvxc", "23", "M", "asdf@qq.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdate() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml")) {
            BookService bookService = context.getBean("bookService", BookService.class);
            bookService.updateBook(new Book("1", "fffff", "ccccc", "66", "M", "asdf@qq.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSelectById() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml")) {
            BookService bookService = context.getBean("bookService", BookService.class);
            Book book = bookService.selectById("1");
            System.out.println(book);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAll() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml")) {
            BookService bookService = context.getBean("bookService", BookService.class);
            List<Book> books = bookService.getAll();
            books.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void testGetTotal() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml")) {
            BookService bookService = context.getBean("bookService", BookService.class);
            System.out.println("共有: " + bookService.getTotel());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteBookById() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml")) {
            BookService bookService = context.getBean("bookService", BookService.class);
            bookService.deleteBookById("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testBatchAdd() {
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml")) {
            BookService bookService = context.getBean("bookService", BookService.class);
            List<Object[]> batchArgs = List.of(
                new Object[] { null, "fffff", "ccccc", "23", "M", "asdf@qq.com" },
                new Object[] { null, "fffff", "ccccc", "23", "M", "asdf@qq.com" },
                new Object[] { null, "fffff", "ccccc", "23", "M", "asdf@qq.com" }
            );
            bookService.batchAdd(batchArgs);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //测试完全注解事务
    @Test
    void testTx() {
        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class)) {
            BookService bookService = context.getBean("bookService", BookService.class);
            List<Book> books = bookService.getAll();
            books.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 测试函数式风格注册对象
    @Test
    void testFunctional() {
        try (GenericApplicationContext context = new GenericApplicationContext()) {
            context.refresh();
            context.registerBean("bookService",BookService.class, BookService::new);
            BookService bookService = context.getBean("bookService", BookService.class);
            List<Book> books = bookService.getAll();
            books.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 测试JUnit5
    @Test
    void testJUnit5() {
        List<Book> books = bookService.getAll();
        books.forEach(System.out::println);
    }
}
