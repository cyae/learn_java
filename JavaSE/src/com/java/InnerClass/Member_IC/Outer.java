package com.java.InnerClass.Member_IC;

import com.java.InnerClass.Member_IC.Outer.Inner;

public class Outer {
    public int a = 10;
    private String name = "123";

    // 等价于类Outer的属性
    class Inner {
        int a = 100;

        public void foo() {
            System.out.println(a);
            System.out.println(Outer.this.a);
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
        Outer outer = new Outer();

        // 本来应该写为Outer.Inner inner = outer.new Inner();
        // 但自动导包import com.java.InnerClass.Member_IC.Outer.Inner;
        Inner inner = outer.new Inner();
        inner.foo();
    }
}
