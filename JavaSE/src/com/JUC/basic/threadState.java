package com.JUC.basic;

public class threadState {
    public static void main(String[] args) throws InterruptedException {
        qwe q = new qwe();
        Thread t1 = new Thread(q);
        t1.start();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; ++i) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (i == 3) {
                        try {
                            t1.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        System.out.println("刚创建p线程，状态是：" + t.getState());

        t.start();
        System.out.println("启动p线程，状态是：" + t.getState());

        while (t.getState() != Thread.State.TERMINATED) {
            Thread.sleep(300);
            System.out.println("p线程正在运行，状态是：" + t.getState());
        }

        System.out.println("p线程终止，状态是：" + t.getState());
    }
}

class qwe implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; ++i) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
