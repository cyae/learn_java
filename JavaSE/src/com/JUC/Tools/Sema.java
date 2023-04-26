package com.JUC.Tools;

import java.util.concurrent.Semaphore;

public class Sema {
    // 可伸缩式
    static Semaphore semaObject = new Semaphore(6);

    public static void main(String[] args) throws InterruptedException {
        semaObject.acquire(2);
        semaObject.release(3);
    }
}
