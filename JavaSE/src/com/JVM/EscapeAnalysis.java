package com.JVM;

import com.Ex.school.Person;

/* 
判断是否发生逃逸：创建对象是否可能在方法外调用，无论是否static */
@SuppressWarnings("all")
public class EscapeAnalysis {
    public Person person;

    /*
     * 1. 方法返回对象
     */
    public Person getInstancePerson() {
        return person == null ? new Person() : person;
    }

    /*
     * 2. 为成员属性赋值
     */
    public void setPerson() {
        this.person = new Person();
        Person person = new Person(); // 没有逃逸
    }

    /*
     * 3. 引用成员变量
     */
    public void func() {
        Person person = getInstancePerson();
    }
}
