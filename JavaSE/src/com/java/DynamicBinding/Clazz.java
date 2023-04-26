package com.java.DynamicBinding;

public class Clazz {
    public int i = 10;

    public int sum() {
        return getI() + 10;
    }

    public int getI() {
        return i;
    }

    public int sum1() {
        return i + 10;
    }
}

class Clazz1 extends Clazz {
    // public int i = 20;

    // public int sum() {
    // return getI() + 20;
    // }

    public int getI() {
        return i;
    }

    // public int sum1() {
    // return i + 20;
    // }
}
