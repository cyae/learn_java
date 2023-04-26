package com.java.Enumeration;

public class before {
    public static void main(String[] args) {
        System.out.println(Season1.SUMMER.getName());
    }
}

class Season1 {
    private String name;
    private String desc;

    public static Season1 SPRING = new Season1("spring", "warm");
    public static Season1 SUMMER = new Season1("summer", "hot");
    public static Season1 AUTUMN = new Season1("autumn", "cool");
    public static Season1 WINTER = new Season1("winter", "cold");

    // 模拟单例模式，构造器私有化
    private Season1(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    // 去除setter
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season [desc=" + desc + ", name=" + name + "]";
    }

}