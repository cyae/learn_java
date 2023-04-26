package com.java.Exception;

// 自定义异常一般继承运行时异常
public class myException extends RuntimeException {

    public myException(String message) {
        super(message);
    }

    public static void main(String[] args) {
        int age = 90;
        if (age < 0 || age > 200) {
            throw new myException("invalid age");
        }
    }

}
