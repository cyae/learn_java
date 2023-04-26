package com.java.String_;

import java.util.Arrays;

// 指导思想：String安全，可重用性高，但效率低。对方法区常量池的一切操作都会创建新字符串，原字符串因为安全考虑，可能其他索引还指向源字符串，故留存
// 高可用性：无论结果是在堆中还是在常量池中，结果已存在就尽量用新指针指向，节省内存
// StringBuilder/Buffer 相对不安全，可重用性低，但效率高
public class StringInit {
    // 类似Integer.valueOf([-128, 127])，如果常量池有，就直接指向不创建
    // 如果没有，就在 常量池 创建
    // 最终直接从栈->常量池地址空间
    static String s1 = "hsp";
    static String s2 = "hsp";

    // new在堆中创建value[]指针，如果常量池有"hsp"，就直接指向常量池中地址。
    // 否则在常量池创建，然后s1从栈->堆中new结构->常量池value[]地址空间，
    // 共创建了1个池外堆中new结构 + 1个常量池中value[]对象
    static String s3 = new String("hsp");

    public static void main(String[] args) {
        System.out.println(s1 == s2); // true
        System.out.println(s2 == s3); // false

        // String.intern()方法：返回常量池地址
        System.out.println(s1 == s3.intern()); // true
        System.out.println(s3 == s3.intern()); // false

        // 创建了1个对象
        // 由于这样写，"hello"和"world"都无法再次使用(匿名)
        // 所以底层优化为String a = "helloworld", 防止浪费
        String a = "hello" + "world";

        // 创建了2个池中string对象(a,b) +
        // 1个匿名stringbuilder + sb.toString()时的1个堆中new String(value[], 0, count)
        // ❗new String(value[], 0, count)方法不同于new String(""),
        // 不会在常量池新建字符串，而是在堆中修改(截取)value[]

        // 这样写，"helloworld"和"!"还可能再次使用(不是匿名，有引用)
        // 但c是通过stringbuilder.append(a,b).tostring()方法新建value[]->常量池地址空间，

        String b = "!";
        String c = a + b;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        // 调用c.intern()方法时，如果常量池中没有结果，会在池中动态指向堆中value[]="helloworld!"的地址(节省空间)，故返回true
        System.out.println(c == c.intern()); // true

        String s1 = "javaEE";
        String s2 = "hadoop";
        // String s3 = "javaEEhadoop"; // 如果常量池中已有结果，那么intern方法就不会逆向指回堆，以下3个全false
        String s4 = s1 + s2;
        String s5 = s1 + "hadoop";
        System.out.println(s4 == s4.intern()); // true，s4.intern()从常量池指回堆，堆中value[]是实体，(高可用性)
        System.out.println(s4 == s5.intern()); // true，s5.intern()从常量池指回堆中s4的value[]实体，(高可用性)
        System.out.println(s5 == s5.intern()); // false，s5指向自身value[]，而s5.intern()从堆中value[]指回s4的value[]实体

        Dog dog = new Dog();
        dog.func1(dog.getName1());
        System.out.println(dog.getName1());
        dog.func2(dog.getName2());
        System.out.println(dog.getName2());
        dog.func3(dog.getName3());
        System.out.println(Arrays.toString(dog.getName3()));
    }
}

class Dog {
    String name = "Jack";
    int i = 1;
    char[] chs = { '1', '2', '3' };

    public void func1(String name) {
        name = "Jose"; // 不会改变,这里只是形参与属性重名,改形参不影响属性。按值传递，将实参的值赋给形参。
    }

    public void func2(int i) {
        i = 2; // 不会改变, 这里只是形参与属性重名, 改形参不影响属性。按值传递，将实参的值赋给形参。
    }

    public void func3(char[] chs) {
        chs[0] = 'X'; // 会改变, 数组按地址传递，将实参的地址赋给形参。
    }

    public int getName2() {
        return this.i;
    }

    public String getName1() {
        return this.name;
    }

    public char[] getName3() {
        return this.chs;
    }
}
