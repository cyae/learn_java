package com.java.Interface;

public interface USBInterface {
    // 接口属性(默认)必须是 public static final的，对比抽象类无此限制
    int a = 10;

    // jdk8以后，接口可以有static方法和default方法
    // 对比抽象类可以有非抽象方法
    // 静态方法只能本接口调用,充当工具，无法被实现
    static void welcome() {
        System.out.println("USB");
    }

    // jdk9接口方法可以被private修饰, 但不可以protected，抽象方法相反
    private void f() {
        System.out.println("!");
    };

    // 接口方法也必须被实现类 全部 重写，除非：
    // 1.这个接口方法是default的
    default void start() {
        System.out.println("USB interface started");
    };

    // 2.或实现类是抽象类
    abstract class A implements USBInterface {
    }

    // 3.或继承类是接口类
    interface B extends USBInterface {
    }

    default void stop() {
        f();
        System.out.println("USB interface stopted");
    };

    USBInterface foo();
}
