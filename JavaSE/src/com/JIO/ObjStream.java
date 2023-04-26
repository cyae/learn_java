package com.JIO;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjStream {
    public static void main(String[] args) throws Exception {
        String path = "e:\\data.dat";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));

        // oos.writeInt(100); // 100被装箱成Integer implements Serializable
        // oos.writeUTF("str");

        if (save(oos, new Dog(12, "err"))) {
            System.out.println("success");
        } else {
            System.out.println("failed");
        }
        oos.flush(); // 确保写入
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        Object obj = load(ois);

        System.out.println(obj);

        ois.close();

    }

    public static boolean save(ObjectOutputStream oos, Object obj) throws IOException {
        try {
            oos.writeObject(obj);
        } catch (RuntimeException e) {
            System.out.println("NotSerializableException");
            return false;
        }
        return true;
    }

    public static Object load(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        return ois.readObject();
    }

}

@SuppressWarnings("all")
class Dog implements Serializable {
    private transient int age; // transient不会写入，读取时为默认值
    private String name;
    // 两端校验版本用
    private static final long serialVersionUID = 4359709211352400087L;

    public Dog(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dog [age=" + age + ", name=" + name + "]";
    }

}
