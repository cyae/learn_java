package com.Ex.salary;

public class Waiter extends Employeer {
    public Waiter(double salary) {
        super(salary);
    }

    @Override
    public void annual() {
        System.out.println("Wa " + super.getAnnual());
    }
}
