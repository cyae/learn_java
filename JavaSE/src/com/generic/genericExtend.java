package com.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Father
 */
class Father {

}

class Son extends Father {

}

public class genericExtend {
    public static void main(String[] args) {
        // 泛型不能直接继承
        // List<Object> list = new ArrayList<String>();

        List<Object> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        List<Father> list4 = new ArrayList<>();
        List<Son> list5 = new ArrayList<>();

        // Object不是String，
        // func(list1);
        func(list2);

        // Oject不是func1参数中，泛型Father的子类
        // func1(list1);

        // Father, Son是func1参数中，泛型Father的子类
        func1(list4);
        func1(list5);

        // Object, Father, Son是func2参数中，泛型Son的父类
        func2(list1);
        func2(list4);
        func2(list5);

        // Integer不是func2参数中，泛型Son的父类
        // func2(list3);

        // func3中的泛型没有限制，相当于<? extends Object>
        general(list3);

    }

    public static void func(List<String> list) {
        System.out.println(list);
    }

    public static void func1(List<? extends Father> list) {
        System.out.println(list);
    }

    public static void func2(List<? super Son> list) {
        System.out.println(list);
    }

    public static void general(List<?> list) {
        System.out.println(list);
    }

    // 只有Object及其父类可以传递进来
    public static void mean(List<? super Object> list) {
        System.out.println(list);
    }
}
