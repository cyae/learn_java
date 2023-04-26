package com.Design_Pattern.Singleton;

public class Singleton1 {
    public int n1 = 100;
    private static String name;

    // 1. 所有重载的构造器统一对外隐藏，防止A.主进程new对象;B.单例被继承
    private Singleton1() {
    }

    private Singleton1(String name) {
        Singleton1.name = name;
    }

    // 2. 自身在类加载时预创建好单例。
    // 饿汉：只要加载类就会预创建单例，即使并不想使用
    // 比如仅需要使用n1值，也会导致单例创建，浪费内存
    private static final Singleton1 singleton1 = new Singleton1(name);

    // 3. 仅对外暴露静态的getter方法，保证只能通过类名.getter返回预创建单例
    // 如果getter为普通方法，则外部必须new对象调用此方法，而在1.中已禁止new对象
    public static Singleton1 getSingleton() {
        return singleton1;
    }
}

// 防止单例被继承
// class A extends Singleton1 {
// @Override
// public static Singleton1 getSingleton() {
// return getSingleton();
// }
// }
