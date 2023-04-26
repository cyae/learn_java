package com.Ex.ProduceConsume;

public class Test {
    public static void main(String[] args) {
        Employee employee = new Employee();
        new Thread(new Producer(employee)::run).start();
        new Thread(new Consumer(employee)::run).start();
    }
}
