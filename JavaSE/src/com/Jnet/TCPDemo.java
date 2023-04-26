package com.Jnet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

public class TCPDemo {

    @Test
    public void client() {
        Socket soc = null;
        OutputStream os = null;
        ByteArrayOutputStream baos = null;
        try {
            // 1. client问好
            InetAddress inet = InetAddress.getByName("localhost");
            int port = 8888;
            soc = new Socket(inet, port);

            os = soc.getOutputStream();
            os.write("hello, i am client".getBytes());

            // 注意, 因为IO.read是阻塞式方法, 如果还有操作(比如下面接收server的got)
            // 客户端输入完成必须关闭os, 否则server一直listen
            soc.shutdownOutput();

            // 4. client接收server的got回复
            InputStream is = soc.getInputStream();
            byte[] buffer = new byte[10];
            int len;
            baos = new ByteArrayOutputStream();
            while ((len = is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            System.out.println(baos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (soc != null)
                try {
                    soc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (baos != null)
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    @Test
    public void server() {
        ServerSocket soc = null;
        InputStream is = null;
        Socket serverSoc = null;
        ByteArrayOutputStream baos = null;
        OutputStream os = null;
        try {
            soc = new ServerSocket(8888);

            serverSoc = soc.accept();
            is = serverSoc.getInputStream();

            // client传入内容可能过大, 导致buffer最多只能留存1024bit窗口大小的内同容
            // byte[] buffer = new byte[1024];
            // int len;
            // while ((len = is.read(buffer)) != -1) {
            // String str = new String(buffer, 0, len);
            // System.out.println(str);
            // }

            // 2. server接收client问好
            byte[] buffer = new byte[10];
            baos = new ByteArrayOutputStream();
            int len;
            while ((len = is.read(buffer)) != -1) {
                // 先使用baos拼接
                baos.write(buffer, 0, len);
            }

            // 3. server回复got
            os = serverSoc.getOutputStream();
            os.write("server: Got".getBytes());

            System.out.println(baos.toString());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (soc != null)
                try {
                    soc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (baos != null)
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (serverSoc != null)
                try {
                    serverSoc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (os != null)
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
