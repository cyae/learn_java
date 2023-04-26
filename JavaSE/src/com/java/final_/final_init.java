package com.java.final_;

class A {
    public final double PI = 3.1415;
    public final double E;
    public final double G;

    public A(double num) {
        E = num;
    }

    {
        G = 9.8;
        System.out.println("code block called");
    }
}

class B {
    // final static 结合使用修饰基本类型和String不会导致类加载
    public static final double PI1 = 3.1415;
    public static final double E1;

    // 不能通过类构造器对static final变量赋值：
    // 构造器的调用依赖new对象，而在此之前类加载时，E1已经是final固定的。
    // public B(double num) {
    // E1 = num;
    // }

    // 普通代码块==构造器的补充，也不行
    // {
    // E1 = 9.8;
    // }

    // 静态代码块可以，在类加载时赋值
    static {
        E1 = 10.0;
        System.out.println("static code block called");
    }
}