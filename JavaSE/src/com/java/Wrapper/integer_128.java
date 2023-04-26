package com.java.Wrapper;

public class integer_128 {
    @SuppressWarnings("all")
    public static void main(String[] args) {

        // 常量池/Integer缓存机制：
        // 取常用的数字在类加载时就缓存，使用时直接取，无需创建对象
        Integer i = Integer.valueOf(1);
        Integer j = Integer.valueOf(1);
        System.out.println(i == j); // true, 直接在常量池中取值
        System.out.println(i.equals(j)); // true, 同一对象地址相同

        // valueOf底层如果在常量池范围内[-128, 127]则直接返回，否则new新Integer对象
        i = Integer.valueOf(128);
        j = Integer.valueOf(128);
        System.out.println(i == j); // false, new了两个Integer对象
        System.out.println(i.equals(j)); // true，两个对象地value属性相同

        i = new Integer(1);
        j = new Integer(1);
        System.out.println(i == j); // false, new了两个Integer对象
        System.out.println(i.equals(j));

        i = new Integer(128);
        j = new Integer(128);
        System.out.println(i == j); // false, new了两个Integer对象
        System.out.println(i.equals(j));

        // 直接赋值基础类型，底层调用等价于valueOf
        i = 1;
        j = 1;
        System.out.println(i == j);
        System.out.println(i.equals(j));

        i = 128;
        j = 128;
        System.out.println(i == j); // false
        System.out.println(i.equals(j));

        // 与基础类型比较，只比较值
        i = 128;
        int k = 128;
        System.out.println(i == k); // true
    }
}
