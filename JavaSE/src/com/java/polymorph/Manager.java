package com.java.polymorph;

public class Manager extends Employee{
    private int bonus;

    public void manage() {
        System.out.println("Manager " + getName() + " is managing...");
    }

    @Override
    public int getAnnual() {
        return super.getAnnual() + bonus;
    }

    public Manager(String name, int salary, int bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
