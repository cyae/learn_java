package com.Design_Pattern.Strategy;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class strategy {

    public static int func(List<Integer> list, Predicate<Integer> strategy) {
        return list.stream()
                   .filter(strategy)
                   .reduce(0, Integer::sum);
    }
    
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println(func(list, e -> true));
        System.out.println(func(list, NumberUtil::isOdd));
        System.out.println(func(list, NumberUtil::isEven));
    }
}

class NumberUtil {

    public static boolean isOdd(Integer num) {
        return num % 2 == 1;
    }

    public static boolean isEven(Integer num) {
        return num % 2 == 0;
    }
}