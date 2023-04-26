package com.JUC.Volatile;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * Data
 */
class Data {
    // 保证可见性, 有序性, 不保证原子性
    volatile int value = 0;

    public void add() {
        this.value = 60;
    }

    public void increment() {
        this.value++;
    }

    // 加悲观锁保证原子性, 但影响效率
    public synchronized void incWithSync() {
        this.value++;
    }

    // 基于乐观锁的AtomicInteger保证原子性
    AtomicInteger val = new AtomicInteger(0);

    public synchronized void incWithAtom() {
        // this.val.incrementAndGet(); // ++i
        this.val.getAndIncrement(); // i++
    }
}

/**
 * volatile关键字：轻量级线程同步机制, 阉割版synchronized
 * 特性:
 * 保证可见性: 当多线程中某线程将操作后的复制变量写回堆内存时, 需要可见机制立即通知其他还未写回的线程, 在各自栈空间里更新原始复制变量
 * 不保证原子性(存在脏写): 假设AB同时修改共享资源, 改完写回时, A被挂起, B写回成功, 但在B的更新通知到达A之前, A被唤醒, 则B的写回被覆盖
 * 保证有序性(禁止指令重排): 在单线程环境下, 指令重排能优化执行效率, 且不会影响最终结果的一致性; 但多线程环境下, 当指令重排涉及共享资源时,
 * 最终一致性会受影响
 * 
 * volatile底层原理:
 * 在机器指令间插入memory barrier, 禁止CPU对内存屏障前后的指令重排
 * 同时强制刷新缓存, 保证所有线程看到最新版本的共享资源
 * 
 * memory barrier分为:
 * 1. store写屏障: 附加在volatile变量的写操作指令前后, 防止内部包裹的volatile写与屏障外的普通/volatile读写重排
 * 2. load读屏障: 附加在volatile变量的读操作指令前后, 防止内部包裹的volatile读与屏障外的普通/volatile读写重排
 * 
 * volatile应用: 单例模式
 */
public class Volatile {
    @Test
    public void testVisibility() {
        Data data = new Data(); // source

        // 其中一个线程更改
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is adding...");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }

            data.add(); // 更新value, 如果有volatile, 会通知其他线程

            System.out.println(Thread.currentThread().getName() + " done, value: " + data.value);
        }, "A").start();

        // main线程监测, 如果有可见机制通知main线程value已经改变, 则能跳出此循环
        while (data.value == 0) {
            ;
        }

        System.out.println("Over! " + Thread.currentThread().getName() + " thinks value is not 0");
    }

    @Test
    public void testAtomicity() {
        Data data = new Data();

        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.increment();
                    data.incWithAtom();
                }
            }, String.valueOf(i)).start();
        }

        // 保证多线程算完后再进行原子性判断
        // try {
        // TimeUnit.SECONDS.sleep(2);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // 保证多线程算完后再进行原子性判断
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }

        if (data.value == 20000) {
            System.out.println("volatile support atomicity, 每个线程的事务都完整");
        } else {
            System.out.println(data.value);
        }

        if (data.val.get() == 20000) {
            System.out.println("atomicInteger support atomicity, 每个线程的事务都完整");
        } else {
            System.out.println(data.val);
        }

    }

    @Test
    public void testRearrange() {
        class Dummy {

            int a = 0;
            int b = 0;

        }
        Dummy dummy = new Dummy();
        // 对单线程而言, 指令重排不影响结果一致性
        // 但多线程同时操作共享资源, 指令重排可能有影响
        new Thread(() -> {
            dummy.a = 5;
            int c = dummy.b;
            System.out.println("c: " + c);
        }).start();
        new Thread(() -> {
            dummy.b = 10;
            int d = dummy.a;
            System.out.println("d: " + d);
        }).start();

    }
}