package com.Ex.salary;

public class Worker extends Employeer {
    public Worker(double salary) {
        super(salary);
    }

    @Override
    public void annual() {
        System.out.println("Wo " + super.getAnnual());
    }
}
