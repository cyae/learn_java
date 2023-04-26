package com.Ex.Bank;

public class CheckingAccount extends BankAccount {

    private final double fee = 1.0;

    public CheckingAccount(double balance) {
        super(balance);
    }

    @Override
    public void deposit(double amount) {
        super.deposit(amount - fee);
    }

    @Override
    public void withdraw(double amount) {
        super.withdraw(amount + fee);
    }
}
