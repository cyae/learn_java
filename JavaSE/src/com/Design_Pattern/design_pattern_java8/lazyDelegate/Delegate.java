package com.Design_Pattern.design_pattern_java8.lazyDelegate;

public class Delegate {

    public static int compute(int n) {
        System.out.println("called...");
        return n * 2;
    }
    
    public static void main(String[] args) {
        int x = 4;
        Lazy<Integer> temp = new Lazy<>(() -> compute(x));
        if (x > 5 && temp.get() > 7) {
            System.out.println(1);
        } else {
            System.out.println(2);
        }

        int tmp = compute(x);
        if (x > 5 && tmp > 7) {
            System.out.println(1);
        } else {
            System.out.println(2);
        }
    }
}
