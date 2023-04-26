package com.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Class_ {
    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception {
        String clsPath = "com.reflection.Cat";
        Class<?> clazz = Class.forName(clsPath);
        System.out.println(clazz);
        System.out.println(clazz.getPackage());
        System.out.println(clazz.getName());
        System.out.println(clazz.getSimpleName());

        // 不含Declared的API只能获得public修饰的属性、方法、构造器...
        Constructor<?> constructor = clazz.getDeclaredConstructor(String.class, int.class);
        Cat cat = (Cat) constructor.newInstance("123", 234);
        System.out.println(cat);
        Field name = clazz.getField("name");
        System.out.println(name.get(cat));

        clazz.getField("name").set(cat, "456"); // 如果name是静态属性，obj也可写成null
        System.out.println(name.get(cat));

        // getXXXs能获得本类及直接父类的XXX属性、方法...
        // 但除了父类的构造器，因为构造器只能在子类调用，无法继承
        // getConstructors返回本类所有重载的构造器
        Field[] fields = clazz.getFields();
        System.out.println("=======list=======");
        for (Field field : fields) {
            System.out.println(
                    "Field Name: " + field.getName() +
                            ", Field Type: " + field.getType() +
                            ", Field Value: " + field.get(cat));
        }

        System.out.println("=======list=======");
        Class<?> clazz2 = Class.forName("com.reflection.Siamese");
        Class<?> clazz3 = clazz2.getSuperclass();
        System.out.println(clazz3);
        Field[] fields2 = clazz2.getFields(); // 本类及父类的公有属性
        Field[] fields3 = clazz2.getDeclaredFields(); // 本类的所有任意权限属性
        Field field4 = clazz2.getField("name"); // 本类的公有属性
        Field field5 = clazz2.getDeclaredField("name"); // 本类的任意权限属性
        Constructor<?> constructor2 = clazz2.getDeclaredConstructor();
        Siamese siamese = (Siamese) constructor2.newInstance();
        for (Field field : fields2) {
            System.out.println(
                    "Field Name: " + field.getName() +
                            ", Field Type: " + field.getType() +
                            ", Field Value: " + field.get(siamese) +
                            ", Fielad Modifier: " + field.getModifiers()); // 默认0，public1、private2、protected4、static8、final16，混合相加
        }

        Class<?>[] interfaces = clazz2.getInterfaces();
        System.out.println(interfaces[0]);

        Annotation[] annotations = clazz2.getAnnotations();
        System.out.println(annotations[0]);

        Method method = clazz2.getMethod("cry");
        Class<?> cryReturnClazz = method.getReturnType();
        System.out.println(cryReturnClazz);
        Method staticMethod = clazz2.getDeclaredMethod("fly");
        staticMethod.setAccessible(true);
        staticMethod.invoke(null);

        Constructor<?> constructor3 = clazz2.getDeclaredConstructor(String.class, int.class);
        constructor3.setAccessible(true); // 暴力破解，即使是private也能使用
        Siamese siamese1 = (Siamese) constructor3.newInstance("123", 234);
        System.out.println(siamese1);

    }
}
