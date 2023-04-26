package com.Design_Pattern.Template_.before;

public class Task1 {

    @SuppressWarnings("all")
    public void job() {
        long t1 = System.currentTimeMillis();

        String s = "";
        for (int i = 0; i < 3000; i++) {
            s += "1";
        }

        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}
