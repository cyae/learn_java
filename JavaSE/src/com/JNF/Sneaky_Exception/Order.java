package com.JNF.Sneaky_Exception;


public class Order {
    private String date;
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    private Integer price;
    private Integer Id;
    public Order(Integer price, Integer id) {
        this.price = price;
        Id = id;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    @Override
    public String toString() {
        return "Order [Id=" + Id + ", date=" + date + ", price=" + price + "]";
    }
    
}
