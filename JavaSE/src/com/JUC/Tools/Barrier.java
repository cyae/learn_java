package com.JUC.Tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

// 伸展式, 多线程集结, 初始为0, 每次+1, 直到parties才解除阻塞
public class Barrier {
    public static void main(String[] args) throws InterruptedException {

        CyclicBarrier barrier = new CyclicBarrier(7, () -> {
            System.out.println("OK");
        });

        for (int i = 0; i < 7; i++) {
            final int tmp = i;
            new Thread(() -> {
                try {
                    System.out.println(tmp);
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}