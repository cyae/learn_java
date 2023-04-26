package com.JVM;

import java.util.concurrent.TimeUnit;

import com.Util.Person;

public class EscapeAnalysis2 {
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        // 分别使用+-DoEscape和PrintGC查看：执行时间和是否发生GC
        for (int i = 0; i < 10_000_000; ++i) {
            alloc();
        }

        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");

        TimeUnit.MINUTES.sleep(3);
    }

    @SuppressWarnings("unused")
    public static void alloc() {
        Person person = new Person();
    }
}
