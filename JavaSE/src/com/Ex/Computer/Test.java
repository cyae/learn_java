package com.Ex.Computer;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String op = sc.nextLine();

        new Cellphone(op).testWork((double n1, double n2) -> {
            switch (Cellphone.getOp()) {
                case "+" -> {
                    return n1 + n2;
                }
                case "-" -> {
                    return n1 - n2;
                }
                case "*" -> {
                    return n1 * n2;
                }
                case "/" -> {
                    return n1 / n2;
                }
                default -> {
                    return -1;
                }
            }
        }, 1.0, 2.0);

        sc.close();
    }
}
