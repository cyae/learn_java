package com.Design_Pattern.Proxy.DynamicProxy;

public interface ClothFactory {
    void produceCloth();
}

class NikeFactory implements ClothFactory {
    @Override
    public void produceCloth() {
        System.out.println("\tProducing clothes via Nike factory...");
        System.out.println("\tDone! Quit Nike factory");
    }
}