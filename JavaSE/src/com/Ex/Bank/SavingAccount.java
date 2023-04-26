package com.Ex.Bank;

public class SavingAccount extends BankAccount {
    private int freeCount = 3;
    private double rate = 0.01;
    private double fee = 1.0;

    public void earnMonthlyInterst() {
        super.deposit(getBalance() * rate);
        setFreeCount(3);
    }

    @Override
    public void deposit(double amount) {
        if (getFreeCount() > 0) {
            super.deposit(amount);
            --freeCount;
        } else {
            super.deposit(amount - fee);
        }
    }

    @Override
    public void withdraw(double amount) {
        if (getFreeCount() > 0) {
            super.withdraw(amount);
            --freeCount;
        } else {
            super.withdraw(amount + fee);
        }
    }

    public SavingAccount(double balance) {
        super(balance);
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getFreeCount() {
        return freeCount;
    }

    public void setFreeCount(int freeCount) {
        this.freeCount = freeCount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
