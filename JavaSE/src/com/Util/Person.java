package com.Util;

/**
 * Person
 */
public record Person(String name, int age) {
    public Person() {
        this(null, 0);
    }

    public Person(String name) {
        this(name, 0);
    }

    public Person(int age) {
        this(null, age);
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
