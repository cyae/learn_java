package com.JVM;

@SuppressWarnings("all")
public class StackTest {
    public static void main(String[] args) {
        try {
            StackTest stackTest = new StackTest();
            stackTest.A();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Err");
    }

    public void A() {
        int i = 10;
        int j = 20;

        B();
        System.out.println(10 / 0);
    }

    public void B() {
        int k = 30;
        int m = 40;
    }
}
