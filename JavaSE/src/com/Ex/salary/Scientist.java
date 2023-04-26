package com.Ex.salary;

public class Scientist extends Employeer {
    private double bonus;

    public Scientist(double salary, double bonus) {
        super(salary);
        this.bonus = bonus;
    }

    @Override
    public void annual() {
        System.out.println("S " + (super.getAnnual() + bonus));
    }
}
