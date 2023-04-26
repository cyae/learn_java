package com.reflection;

import java.io.Serializable;

class ReflectInnerClass {
    public Thread t = new Thread(() -> System.out.println("lambda，反射成功"));
    public Runnable ta = new Runnable() {
        public void run() {
            System.out.println("匿名内部类，反射成功");
        };
    };
}

public class AllClass {
    class Cat {
    };

    @SuppressWarnings("all")
    private static class Dog {
        public void cry() {
            System.out.println("Woof!");
        }
    };

    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception {
        class Fox {
        }

        // 可以获取
        Class<String> clazz1 = String.class; // 外部类反射
        Class<Serializable> clazz2 = Serializable.class; // 接口反射
        Class<int[]> clazz3 = int[].class; // 数组反射
        Class<Deprecated> clazz4 = Deprecated.class; // 注解反射
        Class<Thread.State> clazz5 = Thread.State.class; // 枚举反射
        Class<Integer> clazz6 = int.class; // 基础类型反射
        Class<Integer> clazz7 = Integer.class; // 包装类反射
        Class<Void> clazz8 = void.class; // 空类型反射
        Class<?> clazz9 = Class.class; // Class反射

        Class<Cat> clazz10 = Cat.class; // 成员内部类反射
        Class<Dog> clazz11 = Dog.class; // 静态内部类反射
        Class<Fox> class12 = Fox.class; // 局部内部类反射

        ReflectInnerClass ric = new ReflectInnerClass(); // 用外部类封装匿名内部类
        Class<?> clazz13 = ric.getClass(); // 匿名内部类/lambda 反射，把他俩当成外部类的成员
        Runnable r1 = (Runnable) (clazz13.getField("ta").get(ric));
        r1.run();

        Thread r2 = (Thread) (clazz13.getField("t").get(ric));
        r2.run();
    }
}
