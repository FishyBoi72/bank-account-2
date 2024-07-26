package org.example;

import java.util.Scanner;

public class BankAccount {
    private double balance;
    private String accountHolderName;
    private int accountNumber;
    private static int nextAccountNumber = 1;  // Static variable to keep track of the next account number

    // Existing constructor with added account number parameter
    public BankAccount(String accountHolderName, double balance) {
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.accountNumber = nextAccountNumber++;
    }

    // New constructor with no parameters for user input
    public BankAccount() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account holder name: ");
        this.accountHolderName = scanner.nextLine();
        System.out.print("Enter starting balance: ");
        this.balance = scanner.nextDouble();
        this.accountNumber = nextAccountNumber++;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println(accountHolderName + " deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public boolean withdrawal(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println(accountHolderName + " withdrew: $" + amount);
            return true;
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds");
            return false;
        }
    }

    public boolean transfer(BankAccount targetAccount, double amount) {
        if (this.withdrawal(amount)) {
            targetAccount.deposit(amount);
            return true;
        } else {
            return false;
        }
    }

    // Getter methods
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        return "Account Holder: " + accountHolderName + "\nAccount Number: " + accountNumber + "\nBalance: $" + balance;
    }
}