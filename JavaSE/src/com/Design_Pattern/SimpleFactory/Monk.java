package com.Design_Pattern.SimpleFactory;

public class Monk {
    public String name;
    AbstractVehicle abstractVehicle;

    public Monk(String name, AbstractVehicle abstractVehicle) {
        this.name = name;
        this.abstractVehicle = abstractVehicle;
    }

    public void passRiver() {
        // 这样写导致赋初值时传入的交通工具失去意义
        // Boat boat = VehicleFactory.getBoat();
        // boat.work();

        // 先判断是否有交通工具，再多态地使用
        // 同时判断abstractVehicle是否为空，是否为马
        // 注意不能写成：
        // if (abstractVehicle instanceof Horse)，
        // 因为1.abstractVehicle为空时就无法从工厂拿对象;
        // 2.如果后续增加工具，每一种都要判断，复杂
        if (!(abstractVehicle instanceof Boat)) {
            // 接口向上转型
            abstractVehicle = VehicleFactory.getBoat();
        }
        // 动态绑定
        abstractVehicle.work();
    }

    public void normal() {
        if (!(abstractVehicle instanceof Horse)) {
            abstractVehicle = VehicleFactory.getHorse();
        }
        abstractVehicle.work();
    }
}
