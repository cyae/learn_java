package com.Design_Pattern.Singleton;

public class Singleton3 {
    private static volatile Singleton3 instance;

    private Singleton3() {
        System.out.println(Thread.currentThread().getName() + "进行了一次初始化");
    }

    public static Singleton3 getInstence() {
        if (instance == null) { // 多包一层判空，只有有限个等待，后序无需等待
            synchronized (Singleton3.class) { // 虽然加锁, 但是可能有指令重排, 导致DLC失效
                if (instance == null) {
                    // 正常new对象过程: 1.分空间 2.初始化 3.指针指向, 到达步骤3时, instance非空
                    // 由于步骤2和3不存在依赖关系, 因此可能被重排为132, 即还没完成步骤2的初始化, instance就先被引用指向, 非空了
                    // 在多线程环境下, 如果某线程发生上述132重排, 其他线程在外层if判断instance非空, 就会直接返回未初始化的instance=null;
                    // 而当发生重排的线程完成instance初始化并返回后, 就违背了单例模式的原则
                    // 解决办法: 禁止写指令重排, 使用volatile修饰instance
                    instance = new Singleton3();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {

        // 多线程, instance是共享资源, 需要加锁保证单例
        for (int i = 1; i < 10; i++) {
            new Thread(() -> {
                Singleton3.getInstence();
            }, String.valueOf(i)).start();

        }
    }
}
