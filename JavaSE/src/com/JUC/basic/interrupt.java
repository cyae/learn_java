package com.JUC.basic;

public class interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Man());
        t.start();
        System.out.println("main thread sleep for 2s...");
        System.out.println("after 2s, main will interrupt Man from sleeping");
        Thread.sleep(1000);
        System.out.println("1");
        Thread.sleep(1000);
        System.out.println("2");
        t.interrupt(); // 作用：中断子线程的休眠，让其提前结束休眠
    }
}

class Man implements Runnable {
    @Override
    public void run() {
        while (true) {
            for (int i = 0; i < 3; ++i) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                System.out.println("Man sleep for 5s...");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");// 被main线程中断休眠，继续往下执行
            }
            System.out.println("one round");
        }
    }
}
