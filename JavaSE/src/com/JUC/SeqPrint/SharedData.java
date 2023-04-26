package com.JUC.SeqPrint;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class SharedData {
    private int state = 1;
    private Lock lock = new ReentrantLock(true);
    private Condition key1 = lock.newCondition();
    private Condition key2 = lock.newCondition();
    private Condition key3 = lock.newCondition();

    public void print(int num) throws InterruptedException {
        lock.lock();

        try {
            while (true) {
                // 1. while判断状态
                while (state != num / 5) {
                    key1.await();
                }

                // 2. 业务逻辑
                for (int i = 1; i <= num; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }

                // 3. 通知其他线程，更改状态，体现顺序
                state = state % 3 + 1; // 状态更改在前
                key1.signal(); // 唤醒在后
            }
        } finally {
            lock.unlock();
        }
    }

    // 高内聚低耦合
    public void print5() throws InterruptedException {
        lock.lock();

        try {
            while (true) {
                // 1. while判断状态
                while (state != 1) {
                    key1.await();
                }

                // 2. 业务逻辑
                for (int i = 0; i < 5; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }

                // 3. 通知其他线程，更改状态，体现顺序
                state = 2; // 状态更改在前
                key2.signal(); // 唤醒在后
            }
        } finally {
            lock.unlock();
        }
    }

    public void print10() throws InterruptedException {
        lock.lock();

        try {
            while (true) {// 1. while判断状态
                while (state != 2) {
                    key2.await();
                }

                // 2. 业务逻辑
                for (int i = 0; i < 10; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }

                // 3. 通知其他线程，更改状态，体现顺序
                state = 3;
                key3.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void print15() throws InterruptedException {
        lock.lock();

        try {
            while (true) {// 1. while判断状态
                while (state != 3) {
                    key3.await();
                }

                // 2. 业务逻辑
                for (int i = 0; i < 15; i++) {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName() + "\t" + i);
                }

                // 3. 通知其他线程，更改状态，体现顺序
                state = 1;
                key1.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}