package com.java.Enumeration;

public class after {
    public static void main(String[] args) {
        System.out.println(Season2.SPRING.getName());
        System.out.println(Season2.SPRING);
        for (Season2 string : Season2.values()) {
            System.out.println(string);
        }
        Season2 season2 = Season2.valueOf("SPRING");
        System.out.println(season2.ordinal());
        System.out.println(Season2.SPRING.name());
        System.out.println(Season2.SPRING.ordinal());
    }
}

// 隐式继承Enum类，因为单继承机制，所以无法再继承其他类，但可以实现接口
enum Season2 {
    // 枚举成员必须写在类开头
    SPRING("spring", "warm"),
    SUMMER("summer", "hot"),
    AUTUMN("autumn", "cool"),
    WINTER("winter", "cold"),
    WHAT;

    private String name;
    private String desc;

    // 模拟单例模式，构造器自动私有化
    Season2(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    Season2() {
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
