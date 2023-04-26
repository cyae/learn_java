package com.JNF;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamAPI {
    public static ArrayList<Person> list = new ArrayList<Person>();

    static {
        list.add(new Person("孙悟空", 34));
        list.add(new Person("贝吉塔", 36));
        list.add(new Person("比克", 234));
        list.add(new Person("布欧", 132));
        list.add(new Person("龟仙人", 87));
        list.add(new Person("天津饭", 29));
        list.add(new Person("人造人18", 29));
        list.add(new Person("人造人19", 29));
        list.add(new Person("比鲁斯", 29));
    }

    @Test
    // 有限流，从数组、集合生成
    @SuppressWarnings("all")
    public void test1() {
        Stream<Person> stream = list.stream();
        Consumer<Person> consumer = person -> System.out.println(person.name());
        stream.forEach(consumer);

        Integer[] arr = { 1, 2, 3 };
        Stream<Integer> stream2 = Arrays.stream(arr);

        int[] arr1 = { 1, 2, 3 };
        IntStream stream3 = Arrays.stream(arr1);

        Stream<Integer> stream4 = Stream.of(1, 2, 3);

    }

    @Test
    // 无限流，按规则生成
    public void test2() {
        UnaryOperator<Integer> unaryOperator = t -> t + 2; // 规则
        Stream.iterate(0, unaryOperator).limit(10).forEach(System.out::println);

        Supplier<Double> supplier = () -> Math.random(); // 规则
        Stream.generate(supplier).limit(10).forEach(System.out::println);
    }

    @Test
    // 中间操作 filter skip distinct limit
    public void test3() {
        Stream<Double> stream = Stream.generate(Math::random).limit(10);
        // stream.forEach(System.out::println);
        System.out.println("************************************");
        Predicate<Double> predicate = t -> t > 0.5;
        stream.filter(predicate).forEach(System.out::println);

        System.out.println("************************************");
        Random random = new Random();
        Supplier<Integer> supplier = () -> random.nextInt(10);
        Stream<Integer> stream1 = Stream.generate(supplier).limit(10);
        // distinct()用hashcode和equals判断
        stream1.filter(t -> t > 1).skip(2).distinct().forEach(System.out::println);
    }

    @Test
    // 中间操作：map
    public void test4() {
        List<String> names = List.of("john", "rick", "mary", "jo", "hunter", "mo");
        Function<String, String> function = (t) -> t.toUpperCase();
        names.stream().map(function).forEach(System.out::println);

        System.out.println("************************************");

        list.stream().map(Person::name).filter(name -> name.length() > 2).forEach(System.out::println);

        System.out.println("************************************");

        // flatMap作用类似于list.addAll(list2) => 将list2中每一个元素取出放入list
        // 将stream.flatMap(stream2) => 将stream2中每一个元素取出放入stream
    }

    @Test
    // 中间操作：sorted使用compare方法
    public void test5() {
        // Person没有继承Comparable接口，没有实现compare方法，故排序失败
        // list.stream().sorted().forEach(System.out::println);

        Comparator<Person> cmp = (o1, o2) -> o1.age() - o2.age();
        list.stream().sorted(cmp).forEach(System.out::println);
    }

    @Test
    // 结束操作：match find foreach
    public void test6() {
        boolean allMatch = list.stream().allMatch(person -> person.age() > 100);
        System.out.println(allMatch); // 是否所有人年龄 > 100
        boolean anyMatch = list.stream().anyMatch(person -> person.age() < 50);
        System.out.println(anyMatch); // 是否有人年龄<50
        boolean noneMatch = list.stream().noneMatch(person -> person.name().contains("悟"));
        System.out.println(noneMatch); // 是否没有人名字包含 悟
        Optional<Person> findFirst = list.stream().findFirst();
        System.out.println(findFirst); // 第一个元素
        long count = list.stream().filter(person -> person.name().length() > 3).count();
        System.out.println(count); // 返回long, 因为数据库中存long
        Optional<Person> person = list.stream().max((p1, p2) -> p1.age() - p2.age());
        System.out.println(person);
    }

    @Test
    // 结束操作：reduce
    public void test7() {
        Stream<Integer> stream = Stream.iterate(1, t -> t + 1).limit(10);

        BinaryOperator<Integer> binaryOperator = (num1, num2) -> num1 + num2;
        Optional<Integer> reduced = stream.reduce(binaryOperator);

        System.out.println(reduced);
    }

    @Test
    // 结束操作：collect
    public void test8() {
        List<String> nameList = list.stream().map(Person::name).collect(Collectors.toList());
        System.out.println(nameList);

        String collected = list.stream().map(Person::name).collect(Collectors.joining(","));
        System.out.println(collected);
    }
}
