package com.Design_Pattern.Singleton;

public class Test {
    public static void main(String[] args) {

        System.out.println(Singleton1.getSingleton());
        System.out.println(Singleton1.getSingleton());

        System.out.println(Singleton2.getInstance("123"));
        System.out.println(Singleton2.getInstance());

    }
}
