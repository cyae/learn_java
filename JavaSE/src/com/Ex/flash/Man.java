package com.Ex.flash;

import java.util.Scanner;

@SuppressWarnings("all")
public class Man extends Person {
    static Person man = new Man();
    private int age;

    public Man(int age) {
        super(age);
    }

    public Man() {
        super(1);
    }

    public static void main(String[] args) {
        man.setAge(26);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] mat = new int[2][n];
        for (int i = 0; i < n; i++) {
            mat[0][i] = sc.nextInt();
            mat[1][i] = 2;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(mat[0][i] + " ");
            System.out.println(mat[1][i]);
        }

        sc.close();
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

}
