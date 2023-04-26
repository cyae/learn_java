package com.JNF.optional;

import java.util.Optional;

public class Customer {

    public Customer() {
    }

    public Customer(MemberCard card) {
        this.card = card;
    }

    private MemberCard card;

    public Optional<MemberCard> getCard() {
        return Optional.ofNullable(card);
    }

    public MemberCard oldGetCard() {
        return card;
    }

    public void setCard(MemberCard card) {
        this.card = card;
    }
    
}
