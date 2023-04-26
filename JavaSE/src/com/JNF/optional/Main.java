package com.JNF.optional;

public class Main {
    
    public static void main(String[] args) {
        DiscountService service = new DiscountService();
        System.out.println(service.getDiscount(new Customer(new MemberCard(60))));
        System.out.println(service.getDiscount(new Customer(new MemberCard(10))));

        System.out.println("********************");
        
        DiscountService2 service2 = new DiscountService2();
        System.out.println(service2.getDiscount(new Customer(new MemberCard(60))));
        System.out.println(service2.getDiscount(new Customer(new MemberCard(10))));
        System.out.println(service2.getDiscount(new Customer()));
    }
}
