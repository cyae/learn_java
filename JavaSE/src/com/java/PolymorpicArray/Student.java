package com.java.PolymorpicArray;

public class Student extends Person {
    private double score;

    public Student(String name, int age, double score) {
        super(name, age);
        this.score = score;
    }

    @Override
    public void say(String name, int age) {
        System.out.println("Student " + name + " , age " + age + " score " + score);
    }

    public void study(String name) {
        System.out.println("Student " + name + " is studying ");
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
