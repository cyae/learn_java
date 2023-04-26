package com.learnjava.spring.model;

public class LifeCycle {
    private String id;

    public LifeCycle() {
        System.out.println("无参构造器called...");
    }

    public void setId(String id) {
        this.id = id;
        System.out.println("setter called...");
    }

    public void init() {
        System.out.println("init called...");
    }

    public void destroy() {
        System.out.println("destroy called...");
    }

    @Override
    public String toString() {
        return "服务: 打印LifeCycle{" +
                "id='" + id + '\'' +
                '}';
    }
}
