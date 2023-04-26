package com.java.abstract_;

public class Worker extends Employee {

    public Worker(String name, String id, int salary) {
        super(name, id, salary);
    }

    @Override
    public void work() {
        System.out.println(getName() + " is working...");
    }

    @Override
    public String getName() {
        return "Worker " + super.getName();
    }

    // static方法无法被重写, 试图重写只会被隐藏，不表现多态
    // @Override
    // public void foo() {
    // return;
    // }
}
