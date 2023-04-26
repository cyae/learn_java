package com.Ex.DiscountSystem;

public class Test {
    public static void main(String[] args) {
        DiscountSystem obj = new DiscountSystem();
        obj.addActivity(1, 15, 5, 7, 2);
        obj.consume(101, 16);
        obj.removeActivity(1);
        obj.consume(102, 19);

        // System.out.println("==============================");

        // DiscountSystem obj2 = new DiscountSystem();
        obj.addActivity(4, 10, 6, 3, 2);
        obj.addActivity(2, 15, 8, 8, 2);
        obj.consume(101, 13);
        obj.removeActivity(2);
        obj.consume(101, 17);
        obj.consume(101, 11);
        obj.consume(102, 16);
        obj.consume(102, 21);

        // System.out.println("==============================");

        // DiscountSystem obj3 = new DiscountSystem();
        obj.consume(4, 69);
        obj.consume(4, 82);
        obj.addActivity(5, 45, 19, 3, 2);
        obj.consume(6, 63);
        obj.consume(6, 95);
        obj.addActivity(3, 38, 15, 3, 3);
        obj.consume(6, 70);
        obj.consume(8, 49);
    }
}