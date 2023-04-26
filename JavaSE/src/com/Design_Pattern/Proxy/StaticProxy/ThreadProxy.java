package com.Design_Pattern.Proxy.StaticProxy;

// 静态线程代理：因为Runnable接口没有提供start方法，而运行线程需要start方法
// 所以需要将实现Runnable接口的被代理类传入ThreadProxy构造器，并封装成start方法
// 静态性体现在：在编译时期确定 包装原对象的代理类 和 被代理类
public class ThreadProxy {
    public static void main(String[] args) {
        Tiger tiger = new Tiger(); // 被代理对象

        // 通过代理类Proxy实现调用原对象tiger的方法
        Proxy proxy = new Proxy(tiger);
        proxy.start();

        // 上述Proxy模拟了Thread的工作流程，Thread类即采用了此代理模式
        Thread thread = new Thread(tiger);
        thread.start();

        Thread thread1 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("a");
                    };
                });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            System.out.println("a");
        });
        thread2.start();
    }
}
