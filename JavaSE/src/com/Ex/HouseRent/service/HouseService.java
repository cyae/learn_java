package com.Ex.HouseRent.service;

import com.Ex.HouseRent.model.House;

public class HouseService {

    private static House[] houses;

    public HouseService(int size) {
        houses = new House[size];
    }

    public House[] list() {
        return houses;
    }
}
