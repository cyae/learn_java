package com.learnjava.spring.model;

public class AutoWireDummy {

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AutoWireDummy{" +
                "user=" + user +
                '}';
    }
}
