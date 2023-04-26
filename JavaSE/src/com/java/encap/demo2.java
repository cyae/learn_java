package com.java.encap;

public class demo2 extends demo{
    private String name = "aaa";

    public void bar() {
        super.foo();
        foo();
    }

    @Override
    public void foo() {
        System.out.println(name);
    }
}
