package com.reflection;

public class Cat {
    public Cat cat;
    public String name;
    public int age;

    public Cat() {
    }

    public Cat(Cat cat) {
        this.cat = cat;
    }

    public Cat(String name) {
        this.name = name;
    }

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat [age=" + age + ", name=" + name + "]";
    }

    public void hi() {
        System.out.println("hi");
    }

    public void cry() {
        System.out.println("meow");
    }
}

interface InnerCat {
}

@Deprecated
class Siamese extends Cat implements InnerCat {
    public String name;
    public int age;

    public Siamese() {
    }

    public Siamese(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void cry() {
        super.cry();
    }

    @SuppressWarnings("all")
    private static void fly() {
        System.out.println("siamese is flying");
    }

}
