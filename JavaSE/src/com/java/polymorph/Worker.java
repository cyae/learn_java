package com.java.polymorph;

public class Worker extends Employee{
    public Worker(String name, int salary) {
        super(name, salary);
    }

    @Override
    public int getAnnual() {
        return super.getAnnual();
    }

    public void work() {
        System.out.println("Worker " + getName() + " is working...");
    }
}
