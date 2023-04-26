package com.Ex.school;

public class Test {
    public static void main(String[] args) {
        Person[] person = new Person[4];
        person[0] = new Student("jack", false, 14, "1234");
        person[1] = new Student("rose", true, 16, "2345");
        person[2] = new Teacher("kyle", false, 54, 34);
        person[3] = new Teacher("Paul", false, 29, 3);

        for (var e : person) {
            System.out.println(fun(e));
        }
    }

    public static String fun(Person person) {
        if (person instanceof Student) {
            return person.play(person.getName());
        } else if (person instanceof Teacher) {
            return person.play(person.getName());
        } else {
            return null;
        }
    }
}
