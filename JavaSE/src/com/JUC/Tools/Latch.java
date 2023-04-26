package com.JUC.Tools;

import java.util.concurrent.CountDownLatch;

// 缩小式, 多线程倒计时, 每次-1, 直到count归零才解除阻塞
public class Latch {
    static CountDownLatch latch = new CountDownLatch(6);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                latch.countDown();
            }, String.valueOf(i)).start();
        }

        latch.await();
    }
}
