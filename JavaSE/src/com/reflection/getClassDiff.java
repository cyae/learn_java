package com.reflection;

import java.lang.reflect.Constructor;

public class getClassDiff {
    @SuppressWarnings("all")
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
        // 分别可以在编译阶段、类加载阶段、运行阶段、使用类加载器获得Class对象

        // 1. 编译阶段:已知类名+路径，用静态方法 Class.forName
        // 用于从配置文件中读取路径、加载类
        // 框架里配置文件可以是.xml等等
        String classPath = "com.reflection.Cat";
        Class<?> clazz1 = Class.forName(classPath);
        System.out.println(clazz1);

        // 2. 类加载阶段:已知具体类名，用 类名.class
        // 用于参数传递，比如用于获取类的构造器
        // 最安全，性能高
        Class<Cat> clazz2 = Cat.class;
        System.out.println(clazz2);
        Constructor<?> constructor1 = clazz2.getConstructor(String.class);
        Constructor<?> constructor2 = clazz2.getConstructor(clazz2); // 指向自身的链表

        // 3. 运行阶段：已知类的实例，用 实例.getClass()
        Cat cat = new Cat();
        Class<?> clazz3 = cat.getClass();
        System.out.println(clazz3);

        // 4. 类加载阶段:已知类名+路径，使用4种类加载器
        ClassLoader classLoader = cat.getClass().getClassLoader();
        Class<?> clazz4 = classLoader.loadClass(classPath);
        System.out.println(clazz4);

        // 类加载过程只有一次，所以这些方式得到的Class对象相等
        if (clazz1 == clazz2 && clazz2 == clazz3 && clazz3 == clazz4) {
            System.out.println(true);
        }

        // 5. 基本数据类型
        Class<Integer> clazz5 = int.class;

        // 6. 包装类
        Class<Integer> class6 = Integer.TYPE;

        Class<Integer> class7 = Integer.class;

        System.out.println(clazz5 == class6);
        System.out.println(class6 == class7);
    }
}
