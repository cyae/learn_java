package com.Design_Pattern.SimpleFactory;

public class VehicleFactory {

    private static Horse horse = new Horse();
    private static Boat boat = new Boat();

    public VehicleFactory() {
    }

    // 工厂类属于工具，方法写成static
    public static Horse getHorse() {
        // 根据实际需求，过河只需一匹马，不用每次创建，造成浪费，因此改成单例
        // return new Horse();

        return horse;
    }

    public static Boat getBoat() {
        return boat;
    }
}
