package com.java.Interface;

public class Camera implements USBInterface {

    @Override
    public USBInterface foo() {
        return null;
    }

    @Override
    public void start() {
        USBInterface.super.start();
    }

    // default接口方法可以不被重写
    // @Override
    // public void stop() {
    // USBInterface.super.stop();
    // }
}
