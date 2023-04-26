package com.Design_Pattern.design_pattern_java8.lazyDelegate;

import java.util.function.Supplier;

public class Lazy<T> {
    private T instance;
    private Supplier<T> supplier;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        if (instance == null) {
            instance = supplier.get();
            supplier = null;
        }

        return instance;
    }
    
}
