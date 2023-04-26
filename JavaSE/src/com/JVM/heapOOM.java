package com.JVM;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.Util.Person;

@SuppressWarnings("all")
public class heapOOM {
    public static void main(String[] args) throws InterruptedException {
        Person person1 = new Person();
        Person person2 = new Person();

        int[] arr = new int[10];
        Object[] objArr = new Object[10];

        ArrayList<Person> lisObject = new ArrayList<>();
        // 使用VisualGC观察Eden->from->to->Old->OOM的过程
        while (true) {
            TimeUnit.MICROSECONDS.sleep(100);
            lisObject.add(new Person(UUID.randomUUID().toString(), new Random().nextInt(100)));
        }
    }
}
