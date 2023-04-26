package com.generic;

import java.util.ArrayList;
import java.util.List;

public class generic {

    // 自定义泛型类
    static class Node<T, E> {
        String val;
        T t;
        E e;
        T[] arr;

        public Node(String val, T t, E e, T[] arr) {
            this.val = val;
            this.t = t;
            this.e = e;

            // 泛型数组没法new初始化，因为加载Node时不知道类型T的大小，
            // 泛型T在创建对象时才确定，加载Node类时不知为其分配多少内存
            // this.arr = new T[8];
        }

        // 静态方法在类加载时确定，但泛型在对象创建时才确定
        // 因此静态方法不能包含泛型
        // public static E getE() {
        // t;
        // return this.e;
        // }

        public T getT() {
            return this.t;
        }
    }

    @SuppressWarnings("all")
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        // 添加类型可以是泛型类型的子类
        list.add("EEE"); // String
        list.add(new Object()); // Object
        System.out.println(list.get(0).getClass());
        System.out.println(list.get(1).getClass());
        Node<Integer, String>[] arr = new Node[3];
        System.out.println(arr.length);

        List<?> list2 = new ArrayList<>();
        // list2.add(1); // 使用万能修饰符?作为泛型，那么只能查看不能改动
        // List.of()源码中返回了万能修饰符?作为泛型的list，因此不能改动
    }
}
