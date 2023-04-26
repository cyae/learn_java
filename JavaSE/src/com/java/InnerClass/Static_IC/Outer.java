package com.java.InnerClass.Static_IC;

public class Outer {
    public static int a = 10;
    private static String name = "123";

    // 等价于类Outer的成员变量
    static class Inner {
        int a = 100;

        public void foo() {
            System.out.println(a);
            System.out.println(Outer.a);
            System.out.println(name);
        }
    }

    public void fun() {
        Inner inner = new Inner();
        inner.foo();
    }
}

class External {
    public static void main(String[] args) {
        Outer.Inner inner = new Outer.Inner();
        inner.foo();
    }
}
