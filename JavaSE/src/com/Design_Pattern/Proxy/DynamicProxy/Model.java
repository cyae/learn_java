package com.Design_Pattern.Proxy.DynamicProxy;

abstract class Animal {
}

// 1.被代理类tiger已经继承了animal，无法再继承thread类,
// 为解除单继承限制，通过代理模式实现
// 2.只需修改模型tiger就能改变run的具体实现，解耦
class Tiger extends Animal implements Runnable {
    @Override
    public void run() {
        System.out.println("Aww~~~~!");
    }
}