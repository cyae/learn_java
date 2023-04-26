package com.JUC.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABA {
    static AtomicReference<Integer> atomInt = new AtomicReference<Integer>(100);
    static AtomicStampedReference<Integer> atomInt1 = new AtomicStampedReference<Integer>(100, 1);

    public static void main(String[] args) {
        // ABA问题
        /*
         * new Thread(() -> {
         * atomInt.compareAndSet(100, 101);
         * atomInt.compareAndSet(101, 100);
         * }, "A").start();
         * 
         * new Thread(() -> {
         * try {
         * TimeUnit.SECONDS.sleep(1);
         * atomInt.compareAndSet(100, 200);
         * System.out.println(atomInt.get());
         * } catch (Exception e) {
         * }
         * }, "B").start();
         */

        // 解决ABA
        new Thread(() -> {
            int stamp = atomInt1.getStamp();
            System.out.println(Thread.currentThread().getName() + "的第1次版本号" + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
            }

            stamp = atomInt1.getStamp();
            atomInt1.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "的第2次版本号" + atomInt1.getStamp());

            stamp = atomInt1.getStamp();
            atomInt1.compareAndSet(101, 100, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "的第3次版本号" + atomInt1.getStamp());

        }, "C").start();

        new Thread(() -> {
            int stamp = atomInt1.getStamp();
            System.out.println(Thread.currentThread().getName() + "的第1次版本号" + stamp);
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (Exception e) {
            }

            atomInt1.compareAndSet(100, 200, stamp, stamp + 1);
            System.out.println(Thread.currentThread().getName() + "的第2次版本号" + atomInt1.getStamp());

            System.out.println(atomInt1.getReference());
        }, "D").start();
    }
}
