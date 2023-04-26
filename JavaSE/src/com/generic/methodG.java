package com.generic;

public class methodG {
    public <T, E> void func(T t, E e) {
        System.out.println(t);
    }

    public static void main(String[] args) {
        // 类泛型👇
        Fish<String, Integer> fish = new Fish<>();

        // 方法泛型的确定👇，在调用时传值，
        // 比如R被true定义为Boolean类型, E被1定义为Integer类型
        fish.func(true, 1);
    }
}

class Fish<T, E> {
    // 👇泛型方法，<R>提供了泛型，供参数列表R r和返回值R使用
    public <R> R func(R r, E e) { // E e没有使用泛型方法提供的泛型，则使用泛型类提供的E
        return r;
    }

    // 无论类泛型还是方法泛型，只有< >提供的泛型才能被使用

    // 如果没有泛型方法提供<R>，则返回类型R和参数R r会报错
    // public R func1(R r, E e) {
    // return r;
    // }
}
