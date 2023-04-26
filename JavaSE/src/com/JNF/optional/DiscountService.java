package com.JNF.optional;

public class DiscountService {
    
    public String getDiscount(Customer customer) {
        // 如果依赖很多，每一层都要检查是否为空
        if (customer.getCard() == null) {
            return "";
        }
        Integer res = getDiscountPercentage(customer.oldGetCard());
        if (res != null) {
            return "Discount(%): " + res;
        } else {
            return "";
        }
    }

    private Integer getDiscountPercentage(MemberCard card) {
        if (card.getFidelityPoint() >= 100) {
            return 5;
        } else if (card.getFidelityPoint() >= 50) {
            return 3;
        }

        return null;
    }
}
