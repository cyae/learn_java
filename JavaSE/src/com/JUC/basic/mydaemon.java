package com.JUC.basic;

public class mydaemon {
    public static void main(String[] args) throws InterruptedException {
        Daemon daemon = new Daemon();
        // 将线程设置为守护线程，只要所有用户线程结束，守护线程立即结束
        daemon.setDaemon(true);
        daemon.start();

        for (int i = 0; i < 10; ++i) {
            Thread.sleep(1000);
            System.out.println("!!!");
        }

        System.out.println("main线程结束, 守护线程也结束");
    }
}

class Daemon extends Thread {
    @Override
    public void run() {
        // 守护线程一般设置为无限循环
        for (;;) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("??");
        }
    }
}
