package com.Ex.manager;

import java.util.Objects;

public class Student {
    private int ID;
    private String name;
    private int age;
    private String location;

    public Student() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return ID == student.ID && age == student.age && Objects.equals(name, student.name)
                && Objects.equals(location, student.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, age, location);
    }

    public Student(int ID, String name, int age, String location) {
        this.ID = ID;
        this.name = name;
        this.age = age;
        this.location = location;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
