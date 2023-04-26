package com.Ex.Bank;

public class Test {
    public static void main(String[] args) {
        BankAccount account = new SavingAccount(1000);
        System.out.println(account.getBalance());
        account.withdraw(114);
        System.out.println(account.getBalance());
        account.deposit(100);
        System.out.println(account.getBalance());
        account.deposit(100);
        System.out.println(account.getBalance());
        account.withdraw(100);
        System.out.println(account.getBalance());
        ((SavingAccount) account).earnMonthlyInterst();
        System.out.println(account.getBalance());
        account.withdraw(100);
        System.out.println(account.getBalance());
        account.deposit(100);
        System.out.println(account.getBalance());
        account.deposit(100);
        System.out.println(account.getBalance());
        account.withdraw(100);
        System.out.println(account.getBalance());
    }
}
