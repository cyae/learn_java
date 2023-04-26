package com.Ex.ObjectAnalyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import com.java.PolymorpicArray.Person;

public class ObjectAnalyzer {
    ArrayList<Object> vis = new ArrayList<Object>(); // dfs记录已访问

    /*
     * 将任意类型转化为包含其所有字段信息的字符串
     * 
     * @param obj an Object
     * 
     * @return 包含传入类型所有字段[名、值]的字符串
     */
    public String toString(Object obj) throws IllegalArgumentException, IllegalAccessException {
        if (obj == null) {
            return "null";
        }
        if (vis.contains(obj)) {
            return "...";
        }
        vis.add(obj);

        Class<?> clazz = obj.getClass();
        if (clazz == String.class) {
            return (String) obj;
        }
        if (clazz.isArray()) { // 针对数组类型
            String innerRes = clazz.getComponentType() + "[]{";
            for (int i = 0; i < Array.getLength(obj); ++i) {
                if (i > 0) {
                    innerRes += ",";
                }
                Object val = Array.get(obj, i);
                if (clazz.getComponentType().isPrimitive()) {
                    innerRes += val;
                } else {
                    innerRes += toString(val); // 递归调用
                }
            }

            return innerRes + "}";
        }

        String res = clazz.getName();
        do {
            Field[] fields = clazz.getDeclaredFields();
            try {
                AccessibleObject.setAccessible(fields, true);
            } catch (Exception e) {
                ;
            }
            for (Field field : fields) {
                res += "[";
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!res.endsWith("[")) {
                        res += ",";
                    }
                    res += field.getName() + "=";
                    Class<?> type = field.getType();
                    Object val = field.get(obj);
                    if (type.isPrimitive()) {
                        res += val;
                    } else {
                        res += toString(val); // 递归调用
                    }
                }
                res += "]";
                clazz = clazz.getSuperclass(); // 查找父类所有字段
            }
        } while (clazz != null);

        return res;
    }

    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
        ArrayList<Person> list = new ArrayList<Person>();
        list.add(new Person("小红", 12));
        list.add(new Person("小明", 22));
        list.add(new Person("小李", 32));
        list.add(new Person("小王", 42));
        list.add(new Person("小张", 52));

        System.out.println(list);
    }
}
