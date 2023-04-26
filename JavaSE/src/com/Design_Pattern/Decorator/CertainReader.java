package com.Design_Pattern.Decorator;

public class CertainReader extends AbstractReader {
    @Override
    public void readCertainThing() {
        System.out.println("Certain Thing read by CertainReader");
    }

    // @Override
    public void close() {
        System.out.println("CertainReader closed");
    }
}
