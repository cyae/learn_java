package com.Design_Pattern.Singleton;

@SuppressWarnings("all")
public class Singleton2 {
    public int n = 200;
    private static String name;
    // 懒加载：不预先创建单例
    private static Singleton2 singleton;

    private Singleton2() {
    }

    private Singleton2(String name) {
        Singleton2.name = name;
    }

    public static Singleton2 getInstance() {
        // 仅在单例未创建的情况下创建一次，之后都直接返回
        if (singleton == null) {
            singleton = new Singleton2();
        }
        return singleton;
    }

    // 如果重载getter方法，则以第一次调用时传参为准，以后传参不起作用直接返回
    public static Singleton2 getInstance(String name) {
        // 仅在单例未创建的情况下创建一次，之后都直接返回
        if (singleton == null) {
            singleton = new Singleton2(name);
        }
        return singleton;
    }

    // singleton是临界资源，判空 和 赋值 是临界操作
    // 多线程会导致创建多个singleton
    public static synchronized Singleton2 getInstance2() {
        // 效率差，因为即使单例创建完成，后序等待线程仍然要排队并依次拿锁进入判空
        synchronized (Singleton2.class) {
            if (singleton == null) { // 判空
                singleton = new Singleton2(); // 赋值
            }
        }
        return singleton;
    }

    public static synchronized Singleton2 getInstance3() {
        // double check lock效率高
        if (singleton == null) { // 多包一层判空，只有有限个等待，后序无需等待
            synchronized (Singleton2.class) { // Class对象只加载一次，可作为静态方法的锁资源, 但是可能有指令重排, 导致DLC失效
                if (singleton == null) { // 判空
                    singleton = new Singleton2(); // 赋值
                }
            }
        }
        return singleton;
    }
}
