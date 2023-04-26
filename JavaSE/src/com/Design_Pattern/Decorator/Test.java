package com.Design_Pattern.Decorator;

public class Test {
    public static void main(String[] args) {
        CertainReader nodeReader1 = new CertainReader();
        SomeReader nodeReader2 = new SomeReader();
        EncapReader bufferedReader = null;

        bufferedReader = new EncapReader(nodeReader1);
        bufferedReader.readThing();
        System.out.println("MULTI TEST");
        bufferedReader.multiReadThing(5);
        bufferedReader.close();

        bufferedReader = new EncapReader(nodeReader2);
        bufferedReader.readThing();
        System.out.println("MULTI TEST");
        bufferedReader.multiReadThing(3);
        bufferedReader.close();
    }
}
