package com.JUC.ProCon3;

public class Test {
    public static void main(String[] args) {
        Employee employee = new Employee();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    employee.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者A1抢到锁，").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    employee.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者B1抢到锁，").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    employee.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "生产者A2抢到锁，").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    employee.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "消费者B2抢到锁，").start();
    }
}
