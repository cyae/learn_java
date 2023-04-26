package com.Ex.salary;

public class Test {
    public static void main(String[] args) {
        Employeer e1 = new Peasant(4000);
        Employeer e2 = new Scientist(6000, 10000);
        Employeer e3 = new Teacher(4000, 200, 10);
        Employeer e4 = new Waiter(5000);
        Employeer e5 = new Worker(5000);

        e1.annual();
        e2.annual();
        e3.annual();
        e4.annual();
        e5.annual();
    }
}
