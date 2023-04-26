package com.Design_Pattern.SimpleFactory;

public class Test {
    public static void main(String[] args) {
        Monk person = new Monk("唐僧", null);
        person.normal();
        person.passRiver();
        person.passRiver();
        person.passRiver();
        person.normal();
    }
}
