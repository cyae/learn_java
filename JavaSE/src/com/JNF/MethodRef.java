package com.JNF;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

// 方法引用：当lambda已经实现了方法，传递操作(->)也可省略，可以直接引用以实现的方法
// 方法引用也是函数式接口的实例
// 类(或对象)::非静态(或静态)已实现方法名
public class MethodRef {

    @Test
    // 1. 对象::实现方法
    public void test1() {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("hello");

        // System.out输出流对象——println()已实现方法名，与accept具有相同的输入输出形式
        PrintStream ps = System.out;
        Consumer<String> consumer2 = ps::println;
        consumer2.accept("hi");

        System.out.println("**************************************");

        // person对象——name()已实现的方法名，与get()具有相同的输入输出形式
        Person person = new Person("Tom", 12);

        Supplier<String> supplier = () -> {
            return person.name();
        };
        Supplier<String> supplier1 = () -> person.name();
        Supplier<String> supplier2 = person::name;

        System.out.println(supplier.get());
        System.out.println(supplier1.get());
        System.out.println(supplier2.get());
    }

    @Test
    // 2. 类::静态实现方法
    public void test2() {
        Comparator<Integer> cmp = (i1, i2) -> Integer.compare(i1, i2);
        System.out.println(cmp.compare(1, 2));

        // Integer类——compare静态方法
        Comparator<Integer> cmp1 = Integer::compare;
        System.out.println(cmp1.compare(1, 2));

        Integer[] arr = { 3, 2, 1 };
        Arrays.sort(arr, Integer::compare);
        System.out.println(Arrays.toString(arr));

    }

    @Test
    // 3. 类::实现方法
    public void test3() {
        // Integer.compare(i1, i2)
        Comparator<Integer> cmp = (i1, i2) -> Integer.compare(i1, i2);
        System.out.println(cmp.compare(1, 2));

        // Integer类——i1.compareTo(i2)方法，返回类型相同，形式不匹配，默认第一个为调用者
        Comparator<Integer> cmp1 = Integer::compareTo;
        System.out.println(cmp1.compare(1, 2));

        Integer[] arr = { 3, 2, 1 };
        Arrays.sort(arr, Integer::compareTo);
        System.out.println(Arrays.toString(arr));

        System.out.println("**************************************");

        BiPredicate<String, String> biPredicate = (s1, s2) -> s1.equals(s2);
        System.out.println(biPredicate.test("abc", "abc"));

        BiPredicate<String, String> biPredicate1 = String::equals;
        System.out.println(biPredicate1.test("abc", "abd"));

        System.out.println("**************************************");

        Person p = new Person("Tom", 12);
        Function<Person, String> getName = person -> person.name();
        System.out.println(getName.apply(p));

        Function<Person, Integer> getAge = Person::age;
        System.out.println(getAge.apply(p));

    }

    @Test
    // 4. 类::构造器
    public void test4() {
        // 无参构造器
        Supplier<Person> sup = () -> new Person();
        System.out.println(sup.get());

        Supplier<Person> sup1 = Person::new;
        System.out.println(sup1.get());

        System.out.println("**************************************");

        // 单参数构造器
        Function<String, Person> sup2 = (s) -> new Person(s);
        System.out.println(sup2.apply("小明"));

        Function<String, Person> sup3 = Person::new;
        System.out.println(sup3.apply("小白"));

        System.out.println("**************************************");

        // 双参数构造器
        BiFunction<String, Integer, Person> sup4 = (s, i) -> new Person(s, i);
        System.out.println(sup4.apply("小红", 10));

        BiFunction<String, Integer, Person> sup5 = Person::new;
        System.out.println(sup5.apply("小兰", 11));

        System.out.println("**************************************");
    }

    @Test
    // 5. 数组::构造器
    public void test5() {
        Function<Integer, Double[]> function = len -> new Double[len];
        Double[] arr = function.apply(10);
        System.out.println(Arrays.toString(arr));

        System.out.println("**************************************");

        Function<Integer, Double[]> function2 = Double[]::new;
        Double[] arr1 = function2.apply(4);
        System.out.println(Arrays.toString(arr1));
        System.out.println("**************************************");
    }
}
