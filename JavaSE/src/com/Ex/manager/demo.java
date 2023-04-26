package com.Ex.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

@SuppressWarnings("all")
public class demo {
    private static ArrayList<Student> list;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        list = new ArrayList<>();
        while (true) {

            System.out.println("-----欢迎来到学生管理系统-----");
            System.out.println("请选择您的操作：");
            System.out.println("1. 添加学生");
            System.out.println("2. 删除学生");
            System.out.println("3. 修改学生");
            System.out.println("4. 查看所有学生");
            System.out.println("5. 退出");

            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt) {
                case 1 -> creatStudent(list);
                case 2 -> deleteStudent(list);
                case 3 -> updateStudent(list);
                case 4 -> retrieveStudent(list);
                case 5 -> {
                    System.out.println("谢谢使用！");
                    sc.close();
                    return;
                }
                default -> System.out.println("非法操作！");
            }
        }
    }

    public static void creatStudent(ArrayList<Student> list) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入学生信息");

        System.out.print("姓名：");
        String name = sc.nextLine();

        System.out.print("学号：");
        int ID = sc.nextInt();
        sc.nextLine();

        System.out.print("年龄：");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("住址：");
        String location = sc.nextLine();

        Student student = new Student(ID, name, age, location);
        list.add(student);
    }

    private static void deleteStudent(ArrayList<Student> list) {
        if (list.isEmpty()) {
            System.out.println("没有学生！");
            return;
        }
        System.out.println("请输入学号：");
        Scanner sc = new Scanner(System.in);
        int ID = sc.nextInt();
        Iterator<Student> iter = list.iterator();
        while (iter.hasNext()) {
            Student s = iter.next();
            if (s.getID() == ID) {
                iter.remove();
                break;
            }
        }
    }

    private static void updateStudent(ArrayList<Student> list) {
        if (list.isEmpty()) {
            System.out.println("没有学生！");
            return;
        }
        System.out.println("请输入学号：");
        Scanner sc = new Scanner(System.in);
        int ID = sc.nextInt();
        Iterator<Student> iter = list.iterator();
        while (iter.hasNext()) {
            Student s = iter.next();
            if (s.getID() == ID) {
                iter.remove();
                creatStudent(list);
                break;
            }
        }
    }

    private static void retrieveStudent(ArrayList<Student> list) {
        Iterator<Student> iter = list.iterator();
        System.out.println("ID\t\t\tName\t\tAge\t\tLocation");
        System.out.println("-----------------------------------");
        while (iter.hasNext()) {
            Student s = iter.next();
            System.out.print(s.getID() + "\t");
            System.out.print(s.getName() + "\t");
            System.out.print(s.getAge() + "岁\t\t");
            System.out.println(s.getLocation());
        }
    }

}
