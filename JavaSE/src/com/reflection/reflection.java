package com.reflection;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class reflection {
    public static void main(String[] args) throws Exception {
        File file = new File("src\\com\\reflection\\pojo.properties");
        Properties pp = new Properties();
        pp.load(new FileInputStream(file));
        pp.list(System.out);

        // 反射的作用：无需修改源码，只改外部文件，就能实现功能
        // 框架 = 注解 + 反射 + 设计模式
        String classPath = pp.getProperty("class");
        String methodName = pp.getProperty("method");

        // 1. Class：某个类加载后产生的模板类
        // clazz表示Class模板类的对象
        Class<?> clazz = Class.forName(classPath);
        Object obj = clazz.getDeclaredConstructor().newInstance();
        System.out.println(obj);

        // 2. Method表示模板类中的方法
        // 通过clazz得到加载类com.reflection.Cat的hi方法，该方法hi被视为对象Method
        Method method = clazz.getMethod(methodName); // getMethod不能得到private方法

        // 调用Method对象的invoke方法，对象obj作为参数
        // 即方法对象.invoke(对象)<==>与正常的 对象.方法() 形成反射
        method.invoke(obj);

        // 3. Field为模板类中的成员变量
        Field nameField = clazz.getField("name"); // getField不能得到private成员
        System.out.println(nameField.get(obj));

        // 4. Constructor为模板类中的构造器对象
        Constructor<?> nameConstructor1 = clazz.getConstructor();
        nameConstructor1.setAccessible(true);
        nameConstructor1.newInstance();

        Constructor<?> nameConstructor2 = clazz.getConstructor(String.class); // getConstructor()无参构造器
        nameConstructor2.setAccessible(true);
        Object obj1 = nameConstructor2.newInstance("123");
        System.out.println(obj1);

    }
}
