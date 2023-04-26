package com.Jnet;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import org.junit.Test;

public class UDPDemo {

    @Test
    public void sender() {
        DatagramSocket soc = null;
        try {
            soc = new DatagramSocket();
            InetAddress localHost = InetAddress.getLocalHost();

            String str = "hello";
            byte[] data = str.getBytes();

            DatagramPacket pkt = new DatagramPacket(data, 0, data.length, localHost, 8123);

            soc.send(pkt);
        } catch (Exception e) {
        } finally {

            if (soc != null) {
                soc.close();
            }
        }

    }

    @Test
    public void receiver() {
        DatagramSocket soc = null;
        try {
            soc = new DatagramSocket(8123);
            byte[] buffer = new byte[1024];
            DatagramPacket pkt = new DatagramPacket(buffer, 0, buffer.length);
            soc.receive(pkt);

            System.out.println(new String(pkt.getData(), 0, pkt.getLength()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (soc != null) {
                soc.close();
            }
        }

    }
}
