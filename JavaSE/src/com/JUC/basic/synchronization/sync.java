package com.JUC.basic.synchronization;

import java.util.concurrent.TimeUnit;

class Phone {
    synchronized void call() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("call");
    }

    synchronized void sms() {
        System.out.println("sms");
    }

    synchronized static void call1() throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("call1");
    }
}

public class sync {
    public static void main(String[] args) {
        Phone phone = new Phone();

        /* 普通方法 synchronized 锁当前对象 CPU决定谁先拿到锁，拿到锁的锁住对象 */
        new Thread(() -> {
            try {
                phone.call();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> phone.sms()).start();

        /*
         * 两者不是同一把锁👆👇，static方法和普通方法之间不会竞争
         */

        /* 静态方法 synchronized 锁当前类 CPU决定谁先拿到锁，拿到锁的锁住整个类 */
        Phone phone2 = new Phone();
        new Thread(() -> {
            try {
                Phone.call1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> phone2.sms()).start();
    }
}
