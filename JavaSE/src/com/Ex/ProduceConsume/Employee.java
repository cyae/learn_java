package com.Ex.ProduceConsume;

public class Employee {

    static int product = 0;

    protected synchronized void produce(String name) throws InterruptedException {
        // ❗❌不能用if判断是否等待，因为其他线程notifyAll时，被阻塞线程是从wait()处继续执行的
        // if (product >= 20) // 假如用if，就会直接跳出判断继续执行下面的逻辑
        while (product >= 20) { // 而用while，可以保证即使在wait处醒来，仍要循环进行条件判断
            System.out.println("超过20！生产者被阻塞");
            wait(); // 👈在这里被notify，继续执行，如果是if就跳出，while则继续循环判断
        }

        product++;
        notify();
        System.out.println("生产者:" + name + "生产1个产品: " + product);
    }

    protected synchronized void consume(String name) throws InterruptedException {
        // if (product <= 0) { // 同理
        while (product <= 0) {
            System.out.println("小于0！消费者被阻塞");
            wait();
        }

        notify();
        --product;
        System.out.println("消费者:" + name + "消费1个产品: " + product);
    }

}

class Producer extends Employee implements Runnable {
    String name = "A";
    private Employee employee;

    public Producer(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                employee.produce(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends Employee implements Runnable {
    String name = "B";

    private Employee employee;

    public Consumer(Employee employee) {
        this.employee = employee;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(550);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                employee.consume(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}