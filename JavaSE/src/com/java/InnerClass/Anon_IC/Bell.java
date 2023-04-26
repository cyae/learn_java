package com.java.InnerClass.Anon_IC;

public interface Bell {
    void ring();
}

class CellPhone {
    public void alarm(Bell bell) {
        bell.ring();
    }
}