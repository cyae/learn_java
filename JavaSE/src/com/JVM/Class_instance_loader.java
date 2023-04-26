package com.JVM;

import com.Util.Person;

import org.junit.Test;

/* 类是模板只有一份，类加载只有一次，对象是具体实例 */
public class Class_instance_loader {
    @Test
    public void sameClassTemplateLoader_diffInstance() {
        Person person1 = new Person(15);
        Person person2 = new Person("小孔");
        Person person3 = new Person();

        // 对象是具体实例
        System.out.println(person1.hashCode());
        System.out.println(person2.hashCode());
        System.out.println(person3.hashCode());

        System.out.println("************************************");

        Class<? extends Person> class1 = person1.getClass();
        Class<? extends Person> class2 = person2.getClass();
        Class<? extends Person> class3 = person3.getClass();

        // 类是模板只有一份
        System.out.println(class1.hashCode());
        System.out.println(class2.hashCode());
        System.out.println(class3.hashCode());

        System.out.println("************************************");

        ClassLoader classLoader1 = class1.getClassLoader();
        ClassLoader classLoader2 = class2.getClassLoader();
        ClassLoader classLoader3 = class3.getClassLoader();
        // 类加载只有一次
        System.out.println(classLoader1.hashCode());
        System.out.println(classLoader2.hashCode());
        System.out.println(classLoader3.hashCode());
    }

    /*
     * 双亲委派机制：
     * 
     * TOP 启动类 (根) 加载器 BootstrapClassLoader
     * 扩展类加载器 ExtClassLoader
     * 应用程序加载器 AppClassLoader
     * DOWN：用户自定义类加载器
     * 
     * 请求顺序从下至上，加载顺序从上到下。
     * 如果一个类加载器需要加载类，那么首先它会把这个类请求委派给父类加载器去完成，
     * 每一层都是如此。一直递归到顶层，当父加载器无法完成这个请求时，子类才有权去尝试加载。
     * 
     * 1.AppClassLoader收到类加载的请求
     * 2.将这个请求向上委托给父加载器ExtClassLoader去完成，一直向上委托，直到BootstrapClassLoader
     * 3.BootstrapClassLoader检查是否能够加载当前这个类，能加载就结束，使用当前的加载器，
     * 4.否则，抛出异常ClassNotFound，通知子加载器进行加载
     * 
     * 牺牲性能，保证安全
     */

//    protected Class<?> loadClass(String name, boolean resolve)
//            throws ClassNotFoundException {
//        // 加锁，防止多线程加载同一类
//        synchronized (getClassLoadingLock(name)) {
//            // 检查类是否已经被加载，👆两者一同保证只有一个类被加载
//            Class<?> c = findLoadedClass(name);
//            if (c == null) {
//                try {
//                    // 如果父类加载器不为空，那么加载父类加载器
//                    if (parent != null) {
//                        c = parent.loadClass(name, false);
//                    } else { // 否则，一定是到了引导类加载器，从引导类加载器开始加载
//                        c = findBootstrapClassOrNull(name);
//                    }
//                } catch (ClassNotFoundException e) {
//                }
//
//                if (c == null) {
//                    // 如果引导类加载器没找到，则调用findClass(name)向调用者抛出异常，并在findClass中继续加载
//                    c = findClass(name);
//                }
//            }
//            return c;
//        }
//    }
}
