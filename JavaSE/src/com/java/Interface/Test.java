package com.java.Interface;

public class Test {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.start();
        phone.stop();

        Camera camera = new Camera();
        camera.start();
        camera.stop();

        new Computer().workWithUSB(phone);
        new Computer().workWithCD(phone);

        new Computer().workWithUSB(camera);

        phone.workWithUSB(phone);

        System.out.println(Camera.a);
        System.out.println(Phone.a);
        System.out.println(USBInterface.a);

        Phone.welcome();

        USBInterface a = new USBInterface() {
            @Override
            public USBInterface foo() {
                System.out.println("call");
                return this;
            }
        }.foo().foo().foo().foo();
        a.start();
        a.stop();

    }
}
