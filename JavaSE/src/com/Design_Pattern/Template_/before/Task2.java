package com.Design_Pattern.Template_.before;

public class Task2 {

    public void job() {
        long t1 = System.currentTimeMillis();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3000; i++) {
            sb.append("1");
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}
