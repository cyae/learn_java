package com.Jnet;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

public class InetAddressTest {

    @Test
    public void testGetAddr() throws UnknownHostException {
        InetAddress iAddress = InetAddress.getByName("localhost");
        System.out.println(iAddress);
        System.out.println(iAddress.getHostAddress());
        System.out.println(iAddress.getHostName());

        InetAddress bdAddr = InetAddress.getByName("www.baidu.com");
        System.out.println(bdAddr);
        System.out.println(bdAddr.getHostAddress());
        System.out.println(bdAddr.getHostName());

    }

}
