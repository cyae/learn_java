package com.java.abstract_;

abstract public class Employee {
    private String name;
    private String Id;
    private int salary;

    public Employee() {
    }

    public Employee(String name, String id, int salary) {
        this.name = name;
        Id = id;
        this.salary = salary;
    }

    // 抽象类可以有非抽象方法
    // static方法无法被重写, 试图重写只会被隐藏，不表现多态
    // abstract要求子类必须重写，所以两者不能同时修饰一个方法
    public static final void foo() {
        System.out.println("psf");
    }

    abstract public void work();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
