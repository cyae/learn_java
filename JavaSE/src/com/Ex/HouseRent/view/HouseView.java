package com.Ex.HouseRent.view;

import com.Ex.HouseRent.model.House;
import com.Ex.HouseRent.service.HouseService;
import com.Ex.HouseRent.utils.Utility;

public class HouseView {
    private boolean loop = true;
    private char opt = ' ';
    private HouseService houseService = new HouseService(10);

    public void mainMenu() {
        do {
            System.out.println("=========房屋出租系统菜单==========");
            System.out.println("\t\t\t1. 新 增 房 源");
            System.out.println("\t\t\t2. 查 找 房 源");
            System.out.println("\t\t\t3. 删 除 房 屋 信 息");
            System.out.println("\t\t\t4. 修 改 房 屋 信 息");
            System.out.println("\t\t\t5. 显 示 房 屋 信 息");
            System.out.println("\t\t\t6. 退          出");
            System.out.print("请输入你的选择（1-6）：");
            opt = Utility.readChar();
            switch (opt) {
                case '1' -> System.out.println("1");
                case '2' -> System.out.println("2");
                case '3' -> System.out.println("1");
                case '4' -> System.out.println("2");
                case '5' -> {
                    listHouses();
                }
                case '6' -> {
                    loop = false;
                }
                default -> System.out.println("非法选项");
            }
        } while (loop);
    }

    public void listHouses() {
        System.out.println("=========房屋列表==========");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态");
        House[] houses = houseService.list();
        for (int i = 0; i < houses.length; i++) {
            System.out.println(houses[i]);
        }
        System.out.println("=========房屋列表显示完毕==========");
    }
}
