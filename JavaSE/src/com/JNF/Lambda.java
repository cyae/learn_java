package com.JNF;

import org.junit.Test;

/**
 * Lambda
 */
public class Lambda {
    @Test
    public void TestCompute() {
        Computer computer = new Computer();
        System.out.println(computer.set(new Add()).compute(7, 5));
        System.out.println(computer.set(new Substract()).compute(7, 5));
        System.out.println(computer.set(new Multiplicate()).compute(7, 5));
        System.out.println(computer.set(new Divise()).compute(7, 5));

        // lambda本质：函数式接口的实例
        System.out.println(computer.set((num1, num2) -> {
            return num1 + num2;
        }).compute(12, 4));
        System.out.println(computer.set((num1, num2) -> num1 - num2).compute(12, 4));

    }
}

class Computer implements Computable {
    private Computable computable;

    public Computer() {
    }

    @Override
    public int compute(int num1, int num2) {
        return computable.compute(num1, num2);
    }

    public Computable set(Computable computable) {
        this.computable = computable;
        return computable;
    }
}

class Add implements Computable {
    @Override
    public int compute(int num1, int num2) {
        return num1 + num2;
    }
}

class Substract implements Computable {
    @Override
    public int compute(int num1, int num2) {
        return num1 - num2;
    }
}

class Multiplicate implements Computable {
    @Override
    public int compute(int num1, int num2) {
        return num1 * num2;
    }
}

class Divise implements Computable {
    @Override
    public int compute(int num1, int num2) {
        int res = 0;
        try {
            res = num1 / num2;
        } catch (Exception e) {
            System.out.println("除零");
        }
        return res;
    }
}

@FunctionalInterface
// 只有一个抽象方法的接口：函数式接口，可通过lambda创建函数式接口的对象
// 也可手动创建类实现方法/匿名内部类
interface Computable {
    int compute(int num1, int num2);
}
// Java8以前自带函数式接口：
// 1. Runnable <===> void run()、
// 2. Comparator<T> <===> T compare(T t1, T t2)、
// 3. InvocationHandler <===> Object invoke(Object proxy, Method method,
// Object[] args)

// Java8系统自带函数式接口：
// 1. Consumer<T> <===> void accept(T t) // 给一个对象，没有返回值
// 2. Supplier<T> <===> T get() // 无参数(给泛型)，返回T类型的对象
// 3. Predicate<T> <===> Boolean test(T t) // 给一个对象，返回是否满足某条件
// 4. Function<T, R> <===> R apply(T t) // 给一种T类型对象，返回R类型对象