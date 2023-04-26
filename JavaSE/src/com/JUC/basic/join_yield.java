package com.JUC.basic;

public class join_yield {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; ++i) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread1: hello");
                }
            }
        });

        t.start();

        for (int i = 0; i < 20; ++i) {
            if (i == 5) {
                System.out.println("main wait for thread1");
                t.join(); // 子线程插队，主线程等待子进程结束
            }
            Thread.sleep(1000);
            System.out.println("main: hi");

        }
    }
}
