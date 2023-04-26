package com.java.Interface;

public class Computer {

    public void workWithUSB(USBInterface usbInterface) {
        usbInterface.start();
        usbInterface.stop();
    }

    public void workWithCD(CDInterface cdInterface) {
        cdInterface.start();
        cdInterface.stop();
    }
}
