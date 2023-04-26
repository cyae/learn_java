package com.java.InnerClass.Local_IC;

public class Outer {
    private int n = 100;

    public Outer() {
    }

    public void foo() {
        // 等价于方法的局部变量
        class Inner1 {
            // 如果内外有重名，遵循调用就近原则
            // int n = 101;

            public void bar() {
                // 直接访问外部类成员（尽量少用，防止后序内部类添加重名成员导致错误访问）
                System.out.println(n);
                // 假如重名,使用外部类的this指针访问外部成员（推荐使用）
                System.out.println(Outer.this.n);
            }
        }
        // 外部访问内部
        Inner1 inner1 = new Inner1();
        inner1.bar();
    }
}

class Test {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.foo();
    }
}
