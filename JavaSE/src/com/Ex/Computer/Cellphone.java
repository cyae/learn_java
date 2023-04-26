package com.Ex.Computer;

public class Cellphone {
    static String op;

    public Cellphone() {
    }

    public static String getOp() {
        return op;
    }

    public void setOp(String op) {
        Cellphone.op = op;
    }

    public Cellphone(String op) {
        Cellphone.op = op;
    }

    public void testWork(Calculable calculable, double n1, double n2) {
        System.out.println(calculable.work(n1, n2));
    }
}
