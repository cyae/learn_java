package com.JVM;

public class DynamicLinking {
    public DynamicLinking() {
        super();
    }

    public void A() {
    }

    public void B() {
        A();
    }
}
