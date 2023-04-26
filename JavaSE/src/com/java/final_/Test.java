package com.java.final_;

public class Test {
    public static void main(String[] args) {
        System.out.println(new A(1.0).PI);

        // final static 结合使用修饰基本类型和String不会导致类加载
        System.out.println(B.PI1);

        // final仅指变量不可修改，变量指向的真实内容可修改
        final int[] c = { 1, 2, 3 };
        c[0] = 4;

        // 指向不可修改
        // c = {0};
    }
}
