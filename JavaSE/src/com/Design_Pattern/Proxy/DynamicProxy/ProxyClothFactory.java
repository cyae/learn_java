package com.Design_Pattern.Proxy.DynamicProxy;

public class ProxyClothFactory implements ClothFactory {

    private ClothFactory clothFactory;

    public ProxyClothFactory(ClothFactory clothFactory) {
        this.clothFactory = clothFactory;
    }

    @Override
    public void produceCloth() {
        System.out.println("Producing clothes via proxy...");
        this.clothFactory.produceCloth();
        System.out.println("Done! Quit proxy factory");
    }
}
