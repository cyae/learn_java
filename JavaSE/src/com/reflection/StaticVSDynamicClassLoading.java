package com.reflection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class StaticVSDynamicClassLoading {
    public static void main(String[] args) throws Exception {
        System.out.println("=============Static Class Loading============");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        switch (Integer.valueOf(br.readLine())) {
            case 1:
                // Dog dog = new Dog(); // Dog没有导包，静态加载失败
                break;
            case 2:
                Cat cat = new Cat(); // Cat在同目录下，静态加载成功
                cat.cry();
            default:
                break;
        }

        System.out.println("=============Dynamic Class Loading=============");

        switch (Integer.valueOf(br.readLine())) {
            case 1:
                Class<?> clazz = Class.forName("com.reflection.AllClass$Dog");
                Constructor<?> constructor = clazz.getDeclaredConstructor();
                constructor.setAccessible(true);
                Object obj = constructor.newInstance();
                Method method = clazz.getDeclaredMethod("cry");
                method.setAccessible(true);
                method.invoke(obj);
                break;
            case 2:
                Class<?> clazz2 = Class.forName("com.reflection.Cat");
                Constructor<?> constructor2 = clazz2.getDeclaredConstructor(String.class);
                constructor2.setAccessible(true);
                Object cat = constructor2.newInstance("Kitty");
                Method method2 = clazz2.getDeclaredMethod("cry");
                method2.setAccessible(true);
                method2.invoke(cat);

            default:
                break;
        }

        br.close();
    }
}
