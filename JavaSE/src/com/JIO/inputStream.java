package com.JIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class inputStream {
    public static void main(String[] args) throws IOException {
        String rootPath = "e:\\";
        File filObject = new File(rootPath, "1.txt");
        FileInputStream inputStream = null;
        int readByte = 0;
        try {
            inputStream = new FileInputStream(filObject);
            System.out.println("开启");
            while ((readByte = inputStream.read()) != -1) { // inputStream.read()默认读一个byte，失败返回-1
                System.out.println((char) readByte);
            }
        } catch (Exception e) {
            System.out.println("无此文件");
        } finally {
            if (inputStream != null) {
                inputStream.close();
                System.out.println("关闭");
            }
        }

        int readLine = 0;
        byte[] buff = new byte[8];
        try {
            inputStream = new FileInputStream(filObject);
            System.out.println("开启");
            while ((readLine = inputStream.read(buff)) != -1) { // inputStream.read(byte[len])读len个byte，失败返回-1
                System.out.println(new String(buff, 0, readLine));
            }
        } catch (Exception e) {
            System.out.println("无此文件");
        } finally {
            if (inputStream != null) {
                inputStream.close();
                System.out.println("关闭");
            }
        }
    }
}