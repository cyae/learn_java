package com.JUC.basic.InitThread;

public class thread {
    // 启动Java进程后，找到main线程入口
    public static void main(String[] args) {
        // Thread继承了Runnable接口
        // 所以为了使用并行，可以 自定义类：
        // 1.继承 Thread类并重写方法，但如果子类需要继承其他父类，由于单继承机制则需要Runnable
        // 2.实现 Runnable接口及重写方法

        // 创建线程对象
        Cat cat = new Cat(300);
        Cat cat2 = new Cat(500);
        cat.start(); // Java启动线程1, 与main线程(守护线程)共存
        cat.run(); // main线程调用run方法，与线程1并发，并且直到结束前一直阻塞进程2，run方法不会创建新线程

        cat2.start(); // Java启动线程2，在线程1结束前与其并发，结束后与main两者并发

        System.exit(0); // 强制结束Java进程，无论有多少线程在执行
    }
}

class Cat extends Thread {

    final int times;
    int init = 0;

    public Cat(int times) {
        this.times = times;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hi! I am thread: " + Thread.currentThread().getName());
            init++;

            if (init == times) {
                break;
            }
        }
    }
}
