package com.java.abstract_;

public class Test {
    public static void main(String[] args) {
        // 抽象类可以有非抽象方法
        Manager.foo();

        Employee worker = new Worker("jack", "123", 5000);
        Employee manager = new Manager("peter", "234", 6000);

        worker.work();
        manager.work();

    }
}
