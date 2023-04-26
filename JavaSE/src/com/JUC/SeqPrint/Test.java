package com.JUC.SeqPrint;

/**
 * Test
 */
public class Test {

    public static void main(String[] args) {
        SharedData data = new SharedData();

        new Thread(() -> {
            try {
                data.print(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();
        new Thread(() -> {
            try {
                data.print(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();
        new Thread(() -> {
            try {
                data.print(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();
    }
}