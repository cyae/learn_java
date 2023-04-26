package com.java.Arrays_;

import java.util.Arrays;
import java.util.List;

public class arrays_ {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        Integer[] arr = { 3, 2, 1, 6, 3, 5, 9, 128, -128, 127, -127 };
        Arrays.sort(arr, (Integer i1, Integer i2) -> {
            return i1 - i2;
        });
        System.out.println(Arrays.toString(arr));

        int c = Arrays.binarySearch(arr, 999);
        System.out.println(c);
        if (c < 0) {
            System.out.println("应该插入在" + (-c - 1));
        }
        Integer[] b = Arrays.copyOf(arr, arr.length + 1);
        System.out.println(Arrays.toString(b));

        System.out.println(Arrays.equals(arr, b));

        // .asList返回的运行类型是ArrayList
        List<Integer> list = Arrays.asList(arr);
    }
}
