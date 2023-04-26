package com.Ex.HouseRent.model;

public class House {
    private int id;
    private String name;
    private String phoneNumber;
    private String address;
    private int rent;
    private String state;

    @Override
    public String toString() {
        return id +
                "\t\t" + name +
                "\t" + phoneNumber +
                "\t\t" + address +
                "\t" + rent +
                "\t" + state;
    }

    public House(int id, String name, String phoneNumber, String address, int rent, String state) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.rent = rent;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
