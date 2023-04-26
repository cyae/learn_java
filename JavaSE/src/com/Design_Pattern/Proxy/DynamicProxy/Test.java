package com.Design_Pattern.Proxy.DynamicProxy;

public class Test {
    public static void main(String[] args) {
        // 静态代理
        NikeFactory nikeFactory = new NikeFactory();
        // new出来的静态代理类
        ClothFactory proxyClothFactory = new ProxyClothFactory(nikeFactory);
        proxyClothFactory.produceCloth();

        System.out.println("========================");

        // 动态代理
        Runnable tiger = new Tiger();
        // 通过getProxyInstance动态创建的代理类
        Runnable proxyFactory1 = ProxyFactory.getProxyInstance(tiger);
        // 因为getProxyInstance已经强转泛型tiger了，所以无需强转👇
        // Runnable proxyFactory1 = (Runnable) ProxyFactory.getProxyInstance(tiger);
        proxyFactory1.run();

        // Or simply anonymously:
        ProxyFactory.getProxyInstance(tiger).run();

        System.out.println("************************");
        ClothFactory proxyClothFactory2 = ProxyFactory.getProxyInstance(nikeFactory);
        proxyClothFactory2.produceCloth(); // 通用性
    }
}
