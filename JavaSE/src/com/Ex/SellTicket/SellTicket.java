package com.Ex.SellTicket;

// 高内聚、低耦合 + [线程、 操作、 资源类]
public class SellTicket {
    public static void main(String[] args) {
        Window window = new Window(5);

        Thread thread1 = new Thread(window);
        Thread thread2 = new Thread(window);
        Thread thread3 = new Thread(window);

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
