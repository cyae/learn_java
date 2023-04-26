package com.Design_Pattern.SimpleFactory;

class Horse implements AbstractVehicle {
    @Override
    public void work() {
        System.out.println("Riding");
    }
}

class Boat implements AbstractVehicle {
    @Override
    public void work() {
        System.out.println("Crusing");
    }
}
