package com.JUC.ProCon4;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Employee {
    private volatile boolean flag = true;
    private AtomicInteger num = new AtomicInteger();

    BlockingQueue<String> bq = null;

    // 传参永远传接口!!!!!
    public Employee(BlockingQueue<String> bq) {
        this.bq = bq;
        System.out.println("使用" + bq.getClass().getName());
    }

    public void produce() throws InterruptedException {
        String s;
        boolean success;
        while (flag) {
            s = num.incrementAndGet() + "";
            success = bq.offer(s, 2L, TimeUnit.SECONDS);
            if (success) {
                System.out.println(Thread.currentThread().getName() + " 插入id=" + s);
            } else {
                System.out.println("超过2s都没空间, " + Thread.currentThread().getName() + "插入失败");
            }
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(300) + 1);
        }

        System.out.println(Thread.currentThread().getName() + "结束");
    }

    public void consume() throws InterruptedException {
        String s;
        while (flag) {
            s = bq.poll(2L, TimeUnit.SECONDS);

            if (s == null || s.equalsIgnoreCase("")) {
                flag = false;
                System.out.println("超过2s都没产品, " + Thread.currentThread().getName() + "取出失败");
                break;
            }

            System.out.println(Thread.currentThread().getName() + " 取出id=" + s);
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500) + 500);
        }

        System.out.println(Thread.currentThread().getName() + "结束");
    }

    public void stop() {
        flag = false;
    }

    public static void main(String[] args) {
        Employee employee = new Employee(new ArrayBlockingQueue<>(3));

        new Thread(() -> {
            try {
                employee.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "生产者A1").start();

        new Thread(() -> {
            try {
                employee.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "生产者A2").start();

        new Thread(() -> {
            try {
                employee.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "消费者B1").start();

        // new Thread(() -> {
        // try {
        // employee.consume();
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // }, "消费者B2").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            employee.stop();
        }
    }
}
