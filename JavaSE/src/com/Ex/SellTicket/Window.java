package com.Ex.SellTicket;

// 高内聚、低耦合 + [线程、 操作、 资源类]
// 1. 分析需要上锁的代码，缩减范围
// 2. 使用同步代码块或同步方法
// 3. 锁住的对象必须是同一个this，否则锁多个对象，每个对象竞争不同的锁，仍都能访问临界资源
public class Window implements Runnable {

    private static int tickets; // 临界资源
    public static boolean loop = true; // 标志法结束循环
    private static Object obj = new Object(); // Dummy锁资源，最好设为static，保证即使创建多个对象，地址也唯一能锁住

    public Window(int tickets) {
        Window.tickets = tickets;
    }

    @Override
    public void run() { // synchronized不能写在run，入口不能加锁，否则只有一个窗口工作（粒度过大）
        while (loop) {
            sell();
        }
    }

    // 静态方法锁
    public synchronized static void voo() {
        System.out.println("123");
    }

    // 静态代码块锁
    public static void doo() {
        synchronized (Window.class) {// static没有this地址，没法锁，用类.class对象
            System.out.println("123");
        }
    }

    public /* synchronized */ void sell() { // 👈1.业务方法上锁(互斥锁，每个对象都有，锁的是该对象window的地址this)
        synchronized (obj) { // 👈2.代码块锁，可以锁自身this，也可以锁同一对象
            // 退出检测必须放在最前，否则仍可能超卖！
            if (tickets <= 0) {
                System.out.println(tickets + "None!");
                loop = false;
                return;
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("window" + Thread.currentThread().getName() + ": " + (--tickets) + " remaining...");
        }
    }
}
