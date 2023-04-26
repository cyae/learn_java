package com.Ex.salary;

public class Employeer {
    private double salary;

    public void annual() {
        System.out.println("E " + getAnnual());
    }

    public double getAnnual() {
        return salary * 12;
    }

    public Employeer() {
    }

    public Employeer(double salary) {
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
