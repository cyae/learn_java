package com.JUC.SellTicket2;

import java.util.concurrent.locks.ReentrantLock;

// 高内聚、低耦合 + [线程、 操作、 资源类]
public class Ticket {

    private int number = 100;

    ReentrantLock lock = new ReentrantLock();

    public void sell() { // 高内聚：资源类的功能聚在类内部
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + number-- + "张票，还剩" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
