package com.JUC.SellTicket2;

// 高内聚、低耦合 + [线程、 操作、 资源类]
public class Test {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 1; i <= 40; ++i) {
                ticket.sell();
            }
        }, "售票员A").start();

        new Thread(() -> {
            for (int i = 1; i <= 40; ++i) {
                ticket.sell();
            }
        }, "售票员B").start();

        new Thread(() -> {
            for (int i = 1; i <= 40; ++i) {
                ticket.sell();
            }
        }, "售票员C").start();
    }
}
