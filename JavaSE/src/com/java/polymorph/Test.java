package com.java.polymorph;

public class Test {
    public static void showEmpAnnal(Employee e) {
        System.out.println(e.getName() + " gets " + e.getAnnual() +" one year.");
    }

    public static void testWork(Employee e) {
        if (e instanceof Worker) {
            ((Worker) e).work();
        } else if (e instanceof Manager) {
            ((Manager) e).manage();
        } else {
            System.out.println("Not a worker, nor a manager.");
        }
    }

    public static void main(String[] args) {
        Employee peter = new Worker("peter", 5000);
        Employee lucas = new Manager("lucas", 8000, 20000);

        showEmpAnnal(peter);
        showEmpAnnal(lucas);

//        String a = "123";
//        String b = "123";
//        String c = new String("123");
//        System.out.println(a==b);
//        System.out.println(a == c);
//        System.out.println(a.equals(b));
//        System.out.println(a.equals(c));

        testWork(peter);
        testWork(lucas);
    }
}
