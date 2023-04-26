package com.JIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class outputStream {
    public static void main(String[] args) {
        String path = "e:\\1.txt";
        File fileObject = new File(path);
        FileOutputStream output = null;
        String s = "hello, world";
        try {
            output = new FileOutputStream(fileObject, false); // 覆盖式
            // output = new FileOutputStream(fileObject, false); // 追加式
            System.out.println("打开成功");
            byte[] buff = s.getBytes();
            output.write(buff);
        } catch (Exception e) {
            System.out.println("打开失败");
        } finally {
            try {
                output.close();
                System.out.println("关闭成功");
            } catch (IOException e) {
                System.out.println("关闭失败");
            }
        }
        if (fileObject.delete()) {
            System.out.println(fileObject.getAbsolutePath() + "已删除");
        }
    }
}
