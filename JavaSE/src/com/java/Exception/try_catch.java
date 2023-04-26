package com.java.Exception;

public class try_catch {
    public static void main(String[] args) {
        int a = 1;
        int b = 0;
        int c;
        // 编程时自己处理
        // 对比switch-case-default
        try {
            c = a / b;
        } catch (ArithmeticException e) {
            // 如果发生对应异常，则封装成Exception类传递给catch
            e.printStackTrace();
            System.out.println("divided by 0");
            c = -1;
        } catch (Exception e) {
            // 多个catch捕获多个异常
            // 父类异常需要最后捕获,否则只会捕获第一个异常
            c = -2;
        } finally {
            // 无论是否发生异常，finally一定执行
            // 比如文件IO，数据库操作，try部分可能有异常，也可能没有。
            // 但无论如何都要在finally关闭连接，释放资源
            System.out.println("OK");
        }

        try {
            c = a / b;
        } finally {
            System.out.println("不处理异常，遗言");
        }

        System.out.println(c);
    }
}
