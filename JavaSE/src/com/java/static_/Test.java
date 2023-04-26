package com.java.static_;

public class Test {
    private static int a = 9;

    public Test() {
     }

    public static void count() {
        System.out.println(a++);
    }

    public static void main(String[] args) {
        Test.count();
        Test.count();

    }
}
