package com.JIO;

import java.io.File;
import java.io.IOException;

public class newfile {
    public static void main(String[] args) throws IOException {
        // 用于创建一个文件
        String s = "d:\\1.txt";
        File fileObject = new File(s);
        fileObject.createNewFile();

        // 用于同一目录下创建大量文件
        String rootPath = "d:\\";
        File fileObject2 = new File(rootPath);
        File fileObject3 = new File(fileObject2, "2.txt");
        fileObject3.createNewFile();

        // 用于动态目录
        rootPath = "d:\\";
        String dir = "3.txt";
        File fileObject4 = new File(rootPath, dir);
        fileObject4.createNewFile();

        fileObject4.getName(); // 文件名
        fileObject4.getAbsolutePath(); // 绝对路径
        fileObject4.getParent(); // 父目录
        fileObject4.length(); // 字节大小
        fileObject4.exists(); // 是否存在
        fileObject4.isFile(); // 是否文件
        fileObject4.isDirectory(); // 是否目录
        fileObject2.delete(); // 删除目录
        fileObject4.delete(); // 删除文件
        fileObject2.mkdirs(); // 创建多级目录C:/a/b/c
        fileObject2.mkdir(); // 创建单级目录C:/a

    }
}
