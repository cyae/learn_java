package com.java.Enumeration;

public class Need {
    // 有限个单例：不会变，比如季节、性别、国籍。。。
    public static void main(String[] args) {
        Season spring = new Season("spring", "warm");
        Season summer = new Season("summer", "hot");
        Season autumn = new Season("autumn", "cool");
        Season winter = new Season("winter", "cold");
        System.out.println(spring.getName());
        System.out.println(summer.getName());
        System.out.println(autumn.getName());
        System.out.println(winter.getName());
    }
}

class Season {
    private String name;
    private String desc;

    public Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}