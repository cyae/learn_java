package com.JUC.basic.synchronization;

import java.util.concurrent.locks.ReentrantLock;

public class Lock {
    public static void main(String[] args) {
        Window window = new Window();

        new Thread(window::run).start();
        new Thread(window::run).start();
        new Thread(window::run).start();

    }
}

/*
 * ReentrantLock与synchronized区别：
 * 1. 关键字synchronized，
 * 锁的临界资源是对象或代码块，锁作用域(方法或代码块)结束后自动释放锁；
 * 无需new；不可中断; 不公平;
 * 全体唤醒notifyAll或随机唤醒notify
 * 
 * synchronized在字节码层面由1次monitorenter + 1次monitorexit正常退出 + 1次monitorexit异常退出组成
 * 
 * 2. 类ReentrantLock，锁作用域结束后需手动释放临界资源.unlock, 否则变为单线程可能导致饥饿或死锁；
 * 需要new；可超时中断tryLock；可公平;
 * 精确唤醒
 * 
 */

class Window implements Runnable {
    private int ticket = 100;
    private ReentrantLock lock = new ReentrantLock(false);

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                if (ticket > 0) {
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }

        }
    }
}
