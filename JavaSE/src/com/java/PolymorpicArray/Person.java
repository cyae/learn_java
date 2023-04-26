package com.java.PolymorpicArray;

import com.Ex.ObjectAnalyzer.ObjectAnalyzer;

public class Person {
    private int age;
    private String name;

    public Person() {
    }

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        try {
            return new ObjectAnalyzer().toString(this);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return "failed";
    }

    public void say(String name, int age) {
        System.out.println("Person " + name + " , age " + age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
