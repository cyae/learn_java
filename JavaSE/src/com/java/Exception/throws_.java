package com.java.Exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class throws_ {
    public static void main(String[] args) throws IOException, ArithmeticException {
        // throws踢皮球，把异常返回给调用者，直至jvm
        // 所有方法默认throws
        int a = 1 / 0;
        System.out.println(a);

        FileInputStream fileInputStream = new FileInputStream("D:/a.txt");
        fileInputStream.close();

        // 调用方法func抛出编译型异常FileNotFoundException，调用者必须处理
        // 要么继续抛，要么try catch
        func();
    }

    public static void func() throws FileNotFoundException {
        ;
    }

    public void fun() throws RuntimeException {
        String s = "qwe";
        int a = Integer.parseInt(s);
        System.out.println(a);
    }
}

class A extends throws_ {
    @Override
    public void fun() throws ClassCastException {
        // 子类重写方法抛出异常级别<=父类异常级别
        super.fun();
    }
}