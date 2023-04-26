package com.Design_Pattern.Singleton;

/**
 * 使用静态内部类实现单例模式:
 * 1. 原理是jvm类加载的触发时机: 只有static变量被调用时, 所在类才会被初始化
 * 2. jvm保证静态类LazyHolder只被初始化一次, 所以是线程安全的, 单例的
 */
public class Singleton4 {
    private static class LazyHolder {
        static final Singleton4 INSTANCE = new Singleton4();
    }
    
    public static Singleton4 getInstance() {
        return LazyHolder.INSTANCE;
    }

    private Singleton4() {
    }
}
