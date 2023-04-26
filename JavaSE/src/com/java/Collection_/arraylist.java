package com.java.Collection_;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class arraylist {
    transient Object[] elementData; // transient不会被序列化

    public static void main(String[] args) {
        // ArrayList无参构造默认容量10，满了就1.5倍扩容。
        ArrayList<Integer> list = new ArrayList<>();

        // Vector无参构造默认容量10，满了就2倍扩容。
        Vector<Integer> vector = new Vector<>();

        list.add(0);

        // synchronized修饰，线程安全
        vector.add(0);

        for (int i = 0; i < 10; i++) {
            vector.add(i);
        }

        Iterator<Integer> it = vector.iterator();
        while (it.hasNext()) {
            Integer i = it.next();

            // 禁止在增强for、迭代器里删除原集合，next会越界
            // if (i.equals(2)) {
            // vector.remove(i);
            // }

            if (i.equals(2)) {
                it.remove();
            }
        }

        for (Integer integer : vector) {
            System.out.println(integer);
        }
    }
}
