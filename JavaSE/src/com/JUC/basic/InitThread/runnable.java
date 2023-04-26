package com.JUC.basic.InitThread;

public class runnable {
    public static void main(String[] args) {
        // 代理模式
        Dog dog = new Dog(10);
        Thread threadProxy = new Thread(dog);
        threadProxy.start();

        new Thread(new Dog(10)).start();

        new Thread(() -> {
            System.out.println("a");
        }).start();

        new Thread(new Dog(10)::run).start();
    }
}

class Dog implements Runnable {
    final int times;
    int init = 0;

    public Dog(int times) {
        this.times = times;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hi! I am thread: " + Thread.currentThread().getName());
            init++;

            if (init == times) {
                break;
            }
        }
    }
}
