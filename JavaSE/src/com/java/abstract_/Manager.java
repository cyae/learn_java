package com.java.abstract_;

public class Manager extends Employee {

    public Manager(String name, String id, int salary) {
        super(name, id, salary);
    }

    @Override
    public void work() {
        System.out.println(getName() + " is managing...");
    }

    @Override
    public String getName() {
        return "Manager " + super.getName();
    }

    // static方法无法被重写, 试图重写只会被隐藏，不表现多态
    // @Override
    // public void foo() {
    // return;
    // }
}
