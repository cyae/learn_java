package com.Jnet;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

// 协议://主机名:端口/资源目录.../资源#片段?参数列表=参数
public class URLDemo {
    @Test
    public void testURL() throws MalformedURLException {
        URL url = new URL(
                "https://cn.bing.com/th?id=OHR.Mitsumata_ZH-CN9794271032_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp");
        System.out.println(url.getProtocol());
        System.out.println(url.getHost());
        System.out.println(url.getPort());
        System.out.println(url.getPath());
        System.out.println(url.getFile());
        System.out.println(url.getQuery());

    }

    @Test
    public void testGetURL() {

        try (FileOutputStream fos = new FileOutputStream(
                "E:\\Documents\\JavaProjects\\untitled1\\src\\com\\Jnet\\test.jpg");) {
            URL url = new URL(
                    "https://cn.bing.com/th?id=OHR.Mitsumata_ZH-CN9794271032_1920x1080.jpg&rf=LaDigue_1920x1080.jpg&pid=hp");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream is = urlConnection.getInputStream();
            urlConnection.connect();

            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (Exception e) {
        }

    }
}
