package com.Ex.salary;

public class Peasant extends Employeer {
    public Peasant(double salary) {
        super(salary);
    }

    @Override
    public void annual() {
        System.out.println("P " + super.getAnnual());
    }
}
