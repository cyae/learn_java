package com.Ex.school;

@SuppressWarnings("all")
public class Student extends Person {
    private String stu_Id;

    public Student(String name, boolean gender, int age, String stu_Id) {
        super(name, gender, age);
        this.stu_Id = stu_Id;
    }

    public void study() {
        System.out.println("学习");
    }

    @Override
    public String play(String name) {
        return super.play(name) + "足球";
    }
}
