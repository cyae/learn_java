package com.JUC.ProCon3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Employee {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();

        try {
            while (num >= 3) { // 不能用if，防止signalAll虚假唤醒
                System.out.println(Thread.currentThread().getName() + "试图生产，但货物已达到3个，被阻塞");
                condition.await();
            }
            num++;
            System.out.println(Thread.currentThread().getName() + "进行生产，现在有：" + num);
            condition.signalAll();
            System.out.println("生产完毕，通知B1, B2消费");
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
        lock.lock();

        try {
            while (num <= 0) { // 不能用if，防止signalAll虚假唤醒
                System.out.println(Thread.currentThread().getName() + "试图消费，但货物已达到0个，被阻塞");
                condition.await();
            }
            num--;
            System.out.println(Thread.currentThread().getName() + "进行消费，现在有：" + num);
            condition.signalAll();
            System.out.println("消费完毕，通知A1, A2生产");
        } finally {
            lock.unlock();
        }
    }
}
