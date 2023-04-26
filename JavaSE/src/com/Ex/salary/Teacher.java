package com.Ex.salary;

public class Teacher extends Employeer {
    private double classSal;
    private int classDay;

    public Teacher(double salary, double classSal, int classDay) {
        super(salary);
        this.classSal = classSal;
        this.classDay = classDay;
    }

    @Override
    public void annual() {
        System.out.println("T " + (super.getAnnual() + classDay * classSal));
    }
}
