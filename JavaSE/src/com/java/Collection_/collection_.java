package com.java.Collection_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class collection_ {
    @SuppressWarnings({ "all" })
    public static void main(String[] args) {
        class Book {
            String name;
            int pages;

            public Book(String name, int pages) {
                this.name = name;
                this.pages = pages;
            }

            @Override
            public String toString() {
                return "Book [name=" + name + ", pages=" + pages + "]";
            }
        }

        Book book1 = new Book("AAA", 123);
        Book book2 = new Book("BBB", 456);
        Book book3 = new Book("CCC", 789);

        Book[] books = new Book[] { book1, book2, book3, null };

        List<Object> list = Arrays.asList(books);

        Collection<Object> collection = new ArrayList<>();
        collection.addAll(list);

        Iterator<Object> it = collection.iterator();

        while (it.hasNext()) {
            Book book = (Book) it.next();
            System.out.println(book);
        }

        // 重置迭代器，上一个已经到末尾不可使用
        it = collection.iterator();

        // 增强for==简化迭代器
        for (Object object : collection) {
            Book book = (Book) object;
        }

        System.out.println(collection);
    }
}
