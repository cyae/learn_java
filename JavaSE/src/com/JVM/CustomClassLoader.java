package com.JVM;

import java.io.FileNotFoundException;

public class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String customPath) throws ClassNotFoundException {
        try {
            // 自定义方法获取指定路径的字节数组stream
            byte[] stream = getClassFromCustomPath(customPath);
            if (stream == null) {
                throw new FileNotFoundException();
            } else {
                // 字节数组stream转换为Java类，返回clazz
                return defineClass(customPath, stream, 0, stream.length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new ClassNotFoundException();
    }

    private byte[] getClassFromCustomPath(String customPath) {
        // 如果打包时加密了，可以在此解密
        return null;
    }

    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        try {
            Class<?> clazz = Class.forName("customPath", true, customClassLoader);
            Object obj = clazz.getConstructor().newInstance();
            System.out.println(obj.getClass().getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
