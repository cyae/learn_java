package com.JIO;

import java.io.FileReader;
import java.util.Properties;

public class ppConfiger {
    public static void main(String[] args) throws Exception {
        String path = System.getProperty("user.dir") + "\\src\\com\\JIO\\config.properties";
        Properties pp = new Properties();
        pp.load(new FileReader(path));
        pp.list(System.out);
        System.out.println(pp.getProperty("user"));

    }
}
