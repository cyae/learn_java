package com.JUC.basic;

public class WaitNotify {
    public static void main(String[] args) {
        Adder adder = new Adder();
        new Thread(adder::run).start();
        new Thread(adder::run).start();
    }
}

// 实现交替打印
class Adder implements Runnable {
    static int n = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify(); // 这种通信方式必须与synchronized搭配
                if (n <= 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " " + n);
                    n++;

                    try {
                        // wait与sleep区别：会释放锁，sleep占着茅坑不拉屎
                        wait(); // 默认this.wait()，调用者必须与被锁对象一样
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    break;
                }
            }
        }
    }
}
