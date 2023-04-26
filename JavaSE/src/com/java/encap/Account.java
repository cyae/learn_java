package com.java.encap;

public class Account {
    public String name = "ccc";
    private String remain;
    private String password;

    protected void foo() {
        System.out.println("积累");
        System.out.println(name);
    }

    public void setName(String name) {
        if (name.length() < 2 || name.length() > 4) {
            System.out.println("Invalid name! Defaulted as \"一二三\"");
            this.name = "一二三";
        } else {
            this.name = name;
        }
    }

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", remain='" + remain + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Account(String name, String remain, String password) {
        this.setName(name);
        this.setRemain(remain);
        this.setPassword(password);
    }

    public void setRemain(String remain) {
        if (remain.isEmpty() || Integer.parseInt(remain) <= 20) {
            System.out.println("Insufficient remain! Defaulted as 21");
            this.remain = "21";
        } else {
            this.remain = remain;
        }
    }

    public void setPassword(String password) {
        if (password.length() != 6) {
            System.out.println("Invalid password! Defaulted as qweasd");
            this.password = "qweasd";
        } else {
            this.password = password;
        }
    }
}
