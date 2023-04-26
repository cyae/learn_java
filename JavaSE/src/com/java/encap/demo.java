package com.java.encap;

public class demo extends Account{
    public String name = "bbb";
//    public demo() {
//        super();
//    }

    @Override
    protected void foo() {
        System.out.println("中间类");
        System.out.println(name);
    }
}
