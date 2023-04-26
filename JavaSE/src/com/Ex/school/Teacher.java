package com.Ex.school;

@SuppressWarnings("all")
public class Teacher extends Person {
    private int work_age;

    public Teacher(String name, boolean gender, int age, int work_age) {
        super(name, gender, age);
        this.work_age = work_age;
    }

    public void teach() {
        System.out.println("教学");
    }

    @Override
    public String play(String name) {
        return super.play(name) + "象棋";
    }
}
