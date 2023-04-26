package com.JVM;

import org.junit.Test;

/**
 * Finalization
 */
@SuppressWarnings("all")
public class Finalization {

    static Finalization obj; // GC root

    @Test
    public void testNoFinalize() throws InterruptedException {
        obj = new Finalization(); // 建立引用, 指向对象
        obj = null; // 销毁引用, 对象变成垃圾
        System.gc();
        System.out.println("First GC");

        Thread.sleep(2000);
        if (obj == null) {
            System.out.println("obj is dead");
        } else {
            System.out.println("obj is still alive");
        }

        obj = null;
        System.gc();
        System.out.println("Second GC");

        Thread.sleep(2000);
        if (obj == null) {
            System.out.println("obj is dead");
        } else {
            System.out.println("obj is still alive");
        }
    }

    @Test
    public void testFinalize() throws InterruptedException {
        obj = new Finalization() {
            @Override
            protected void finalize() throws Throwable {
                super.finalize();
                System.out.println("调用重写finalize");
                obj = this; // 与引用链上对象建立联系, 可触及
            }
        };
        obj = null;
        System.gc(); // 第一次调用GC, 回收前执行重写的finalize方法
        System.out.println("First GC");

        Thread.sleep(2000);
        if (obj == null) {
            System.out.println("obj is dead");
        } else {
            System.out.println("obj is still alive");
        }

        obj = null;
        System.gc(); // 第二次调用GC, 不再执行finalize方法
        System.out.println("Second GC");

        Thread.sleep(2000);
        if (obj == null) {
            System.out.println("obj is dead");
        } else {
            System.out.println("obj is still alive");
        }
    }

    @Test
    public void localGC1() {
        {
            byte[] buffer = new byte[10];
        }
        System.gc(); // 超出buffer作用域，但不会回收buffer，因为引用还在，对象可达，属于内存泄漏
    }

    @Test
    public void localGC2() {
        {
            byte[] buffer = new byte[10];
        }
        int a = 10;
        System.gc(); // 超出buffer作用域，会回收buffer，因为局部变量量表中slot里的引用被a取代，对象不可达
    }

    @Test
    public void localGC3() {
        localGC1();
        System.gc(); // 会回收，localGC1栈帧弹出，局部变量表被销毁，引用也随之销毁
    }
}