package com.java.Annotation;

import java.util.Vector;

/**
 * Annotation
 */
@SuppressWarnings("all")
public class Annotation {

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        int i = 1;

        @Deprecated
        @SuppressWarnings({ "rawtypes", "unused" })
        Vector a = new Vector<>();

    }

    public int foo() {
        return 0;
    }
}

/**
 * InnerAnnotation
 */
class InnerAnnotation extends Annotation {

    @Override
    public int foo() {
        return 1;
    }
}