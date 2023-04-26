package com.JUC.basic;

public class deadlock {
    public static void main(String[] args) {
        demo demo1 = new demo(true); // demo1锁o1抢o2
        demo demo2 = new demo(false); // // demo2锁o2抢o1

        demo1.start();
        demo2.start();
    }
}

class demo extends Thread {
    static Object o1 = new Object();
    static Object o2 = new Object();
    boolean flag = false;

    public demo(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag) {
            synchronized (o1) {
                System.out.println(Thread.currentThread().getName() + " enter 1");
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + " enter 2");
                }
            }
        } else {
            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + " enter 3");
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + " enter 4");
                }
            }
        }
    }
}