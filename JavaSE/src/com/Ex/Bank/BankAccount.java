package com.Ex.Bank;

public class BankAccount {

    private double balance;

    public BankAccount() {
    }

    public BankAccount(double balance) {
        this.balance = balance;
    }

    // 存款
    public void deposit(double amount) {
        balance += amount;
    }

    // 取款
    public void withdraw(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
