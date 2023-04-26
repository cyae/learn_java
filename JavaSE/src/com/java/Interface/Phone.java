package com.java.Interface;

// 父类和接口若有同名同参数方法，且子类未重写，则默认调用父类的
public class Phone extends Computer implements USBInterface, CDInterface {
    @Override
    public void start() {
        System.out.println("phone connected..");
    }

    @Override
    public void stop() {
        System.out.println("phone disconnected..");
    }

    public static void welcome() {
    }

    @Override
    public USBInterface foo() {
        return null;
    }
}
