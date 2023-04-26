package com.java.Collections_;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class collections {
    public static void main(String[] args) {
        // 工具类
        List<Integer> list = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6 });

        System.out.println(Collections.frequency(list, 2));
        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));

        Collections.shuffle(list);
        System.out.println(list);

        Collections.sort(list);
        System.out.println(list);

        System.out.println(Collections.binarySearch(list, 2));

        Collections.swap(list, 0, 2);
        System.out.println(list);

        Collections.reverse(list);
        System.out.println(list);

        Collections.fill(list, 2);
        System.out.println(list);

        System.out.println(Collections.frequency(list, 2));
    }
}
