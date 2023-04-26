package com.Ex.flash;

public class Login {
    static final int maxTries = 3;
    static final String username = "qweasd123";
    static final String password = "qweasd123";

    public static void main(String[] args) {
        StringBuilder sb1 = new StringBuilder("123");
        StringBuilder sb2 = new StringBuilder("123");
        String s2 = sb2.toString();
        String s1 = sb1.toString();

        System.out.println(s1.equals(s2));
    }
}
