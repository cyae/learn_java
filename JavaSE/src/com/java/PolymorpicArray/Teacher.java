package com.java.PolymorpicArray;

public class Teacher extends Person {
    private int salary;

    public Teacher(String name, int age, int salary) {
        super(name, age);
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public void say(String name, int age) {
        System.out.println("Teacher " + name + " , age " + age + " salary " + salary);
    }

    public void teach(String name) {
        System.out.println("Teacher " + name + " is teaching");
    }
}
