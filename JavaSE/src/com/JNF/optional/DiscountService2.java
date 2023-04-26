package com.JNF.optional;

import java.util.Optional;

public class DiscountService2 {

    public String getDiscount(Customer customer) {
        return customer.getCard()
                       .flatMap(card -> getDiscountPercentage(card))
                       .map(d -> "Discount(%): " + d)
                       .orElseGet(() -> "");
    }

    private Optional<Integer> getDiscountPercentage(MemberCard card) {

        if (card.getFidelityPoint() >= 100) {
            return Optional.of(5);
        } else if (card.getFidelityPoint() >= 50) {
            return Optional.of(3);
        }

        return Optional.empty();
    }
}
