package com.Design_Pattern.Template_.after;

abstract public class AbstractTemplate {

    private long t1;
    private long t2;

    abstract protected void func();

    // 将抽象方法放入非抽象方法内部，可根据实现不同而只改变抽象方法部分
    public void timer() {

        t1 = System.currentTimeMillis();

        // 依赖具体实现，但其他部分模板都一样
        func();

        t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}
