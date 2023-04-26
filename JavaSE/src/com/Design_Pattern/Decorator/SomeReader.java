package com.Design_Pattern.Decorator;

public class SomeReader extends AbstractReader {
    @Override
    public void readSomething() {
        System.out.println("Something read by SomeReader");
    }

    public void close() {
        System.out.println("SomeReader closed");
    }
}
