package com.JNF;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


// 只有一个抽象方法的接口：函数式接口，可通过lambda创建函数式接口的对象
// 也可手动创建类实现方法/匿名内部类

// Java8以前自带函数式接口：
// 1. Runnable <===> void run()、
// 2. Comparator<T> <===> T compare(T t1, T t2)、
// 3. InvocationHandler <===> Object invoke(Object proxy, Method method,
// Object[] args)
// 4. Callable <==> <T> call()

// Java8系统自带函数式接口：
// 1. Consumer<T> <===> void accept(T t) // 给一个对象，没有返回值
// 2. Supplier<T> <===> T get() // 无参数(给泛型)，返回T类型的对象
// 3. Predicate<T> <===> Boolean test(T t) // 给一个对象，返回是否满足某条件
// 4. Function<T, R> <===> R apply(T t) // 给一种T类型对象，返回R类型对象

@SuppressWarnings("all")
public class Functional {

    @Test
    public void test1() {
        Runnable runnable = (() -> System.out.println("123"));
        new Thread(runnable).start();

        Comparator<Integer> comparator = ((i1, i2) -> i1.compareTo(i2));
        Arrays.sort(new Integer[] { 3, 2, 1 }, comparator);

        InvocationHandler iHandler = ((Object obj, Method method, Object[] args) -> {
            System.out.println("Hello"); // 非业务通用方法
            method.invoke(obj, args); // AOP：面向切面变成，核心method可变
            System.out.println("Bye"); // 非业务通用方法
            return null;
        });
    }

    Person person = new Person("小王", 17); // person实例

    @Test
    public void test2() {

        Consumer<Person> consumer1 = new Consumer<Person>() {
            @Override
            public void accept(Person person) { // 这里可以与person实例重名
                System.out.println("个人信息如下：");
                System.out.println(person.getClass().getSimpleName() + ": " + person.age());
                System.out.println(person.getClass().getSimpleName() + ": " + person.name());
            }
        };
        consumer1.accept(person);

        Consumer<Person> consumer2 = (t -> { // 这里t不能与person实例重名
            System.out.println("个人信息如下：");
            System.out.println(t.getClass().getSimpleName() + ": " + t.age());
            System.out.println(t.getClass().getSimpleName() + ": " + t.name());
        });
        consumer1.accept(person);
    }

    @Test
    public void test3() {
        Supplier<Person> supplier1 = new Supplier<Person>() {
            @Override
            public Person get() {
                return new Person("小明", 14);
            }
        };
        Person person = supplier1.get();

        Supplier<Person> supplier2 = (() -> new Person("小明", 14));
        person = supplier2.get();
    }

    @Test
    public void test4() {
        Predicate<Person> predicate1 = new Predicate<Person>() {
            public boolean test(Person t) {
                return t.age() >= 18;
            };
        };
        if (!predicate1.test(person)) {
            System.out.println("未成年");
        } else {
            System.out.println("已成年");
        }

        Predicate<Person> predicate2 = (t -> t.age() >= 18);
        if (!predicate2.test(person)) {
            System.out.println("未成年");
        } else {
            System.out.println("已成年");
        }
    }

    @Test
    public void test5() {
        Function<Person, Integer> ageGetter = new Function<Person, Integer>() {
            public Integer apply(Person t) {
                return t.age();
            };
        };
        System.out.println(ageGetter.apply(person));

        Function<Person, String> nameGetter = (t -> t.name());
        System.out.println(nameGetter.apply(person));
    }
}

/**
 * Person
 */
record Person(String name, int age) {
    Person(String name) {
        this(name, 0);
    }

    Person(int age) {
        this(null, age);
    }

    Person() {
        this(null, 0);
    }
}