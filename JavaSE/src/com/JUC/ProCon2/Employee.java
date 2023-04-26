package com.JUC.ProCon2;

public class Employee {
    int num = 0;

    synchronized void increment() throws InterruptedException {
        while (num >= 3) { // 不能用if，防止notifyAll虚假唤醒
            System.out.println(Thread.currentThread().getName() + "试图生产，但货物已达到3个，被阻塞");
            wait();
        }

        num++;
        System.out.println(Thread.currentThread().getName() + "进行生产，现在有：" + num);
        notifyAll();
        System.out.println("生产完毕，通知B1, B2消费");

    }

    synchronized void decrement() throws InterruptedException {
        while (num <= 0) { // 不能用if，防止notifyAll虚假唤醒
            System.out.println(Thread.currentThread().getName() + "试图消费，但货物已达到0个，被阻塞");
            wait();
        }

        num--;
        System.out.println(Thread.currentThread().getName() + "进行消费，现在有：" + num);
        notifyAll();
        System.out.println("消费完毕，通知A1, A2生产");

    }
}
