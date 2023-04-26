package com.java.PolymorpicArray;

import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {

        Person[] squad = new Person[5];
        squad[0] = new Person("tom", 21);
        squad[1] = new Student("jack", 16, 34.2);
        squad[2] = new Student("mary", 18, 47.8);
        squad[3] = new Teacher("levi", 31, 20000);
        squad[4] = new Teacher("hanji", 35, 23000);
        LinkedList<Person> list = new LinkedList<>();

        for (Person person : squad) {
            list.add(person);
        }

        for (Person person : list) {
            person.say(person.getName(), person.getAge());
            if (person instanceof Student) {
                ((Student) person).study(person.getName());
            } else if (person instanceof Teacher) {
                ((Teacher) person).teach(person.getName());
            } else {
                System.out.println("Not a student, nor a teacher");
            }
        }

        // for (Person person : squad) {
        // person.say(person.getName(), person.getAge());
        // if (person instanceof Student) {
        // ((Student) person).study(person.getName());
        // } else if (person instanceof Teacher) {
        // ((Teacher) person).teach(person.getName());
        // } else {
        // System.out.println("Not a student, nor a teacher");
        // }
        // }

    }
}
