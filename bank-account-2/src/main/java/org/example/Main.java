package org.example;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();

        // Predefined accounts
        BankAccount account1 = new BankAccount("Fish", 500);
        BankAccount account2 = new BankAccount("Dewey", 5000);
        BankAccount account3 = new BankAccount("Tyler", 300);

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);

        System.out.println("Hello world! Welcome to the Bank of Matt!");
        int choice = 0;
        while (choice != -1) {
            System.out.println("Are you an existing customer? (-1 to exit)");
            System.out.println("1. Yes");
            System.out.println("2. No");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                if (choice == 1) {
                    // Existing customer logic
                    System.out.println("Enter your account number: ");
                    int accountNumber = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    BankAccount existingAccount = findAccountByNumber(accounts, accountNumber);
                    if (existingAccount != null) {
                        mainMenu(scanner, existingAccount, accounts);
                    } else {
                        System.out.println("Account not found.");
                    }
                } else if (choice == 2) {
                    System.out.println("Let's make a new account!");
                    System.out.print("What is the name for the account? ");
                    String name = scanner.nextLine();
                    System.out.print("What is the beginning balance for the account? ");
                    double balance = scanner.nextDouble();
                    BankAccount newAccount = new BankAccount(name, balance);
                    accounts.add(newAccount);
                    System.out.println("Account created successfully!\n");
                    mainMenu(scanner, newAccount, accounts);
                } else if (choice != -1) {
                    System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume the invalid input
            }
        }

        // Print all accounts
        System.out.println("Final account details:");
        for (BankAccount account : accounts) {
            System.out.println(account);
        }

        scanner.close();
    }

    private static void mainMenu(Scanner scanner, BankAccount account, ArrayList<BankAccount> accounts) {
        int choice;
        do {
            System.out.println("Hello " + account.getAccountHolderName() + "!");
            System.out.println("Welcome to the Main Menu, what would you like to do today?");
            System.out.println("1. To check account balance");
            System.out.println("2. To make a withdrawal");
            System.out.println("3. To make a deposit");
            System.out.println("4. To make a transfer to another account");
            System.out.println("0. To exit.");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.println("Your account balance is: $" + account.getBalance());
                        break;
                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        scanner.nextLine(); // consume newline
                        account.withdrawal(withdrawAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        scanner.nextLine(); // consume newline
                        account.deposit(depositAmount);
                        break;
                    case 4:
                        System.out.print("Enter the account number to transfer to: ");
                        int targetAccountNumber = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        BankAccount targetAccount = findAccountByNumber(accounts, targetAccountNumber);
                        if (targetAccount != null) {
                            System.out.print("Enter amount to transfer: ");
                            double transferAmount = scanner.nextDouble();
                            scanner.nextLine(); // consume newline
                            if (account.transfer(targetAccount, transferAmount)) {
                                System.out.println("Transfer successful!");
                            } else {
                                System.out.println("Transfer failed due to insufficient funds.");
                            }
                        } else {
                            System.out.println("Target account not found.");
                        }
                        break;
                    case 0:
                        System.out.println("Exiting to main menu...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume the invalid input
                choice = -1; // ensure the loop continues
            }
        } while (choice != 0);
    }

    private static BankAccount findAccountByNumber(ArrayList<BankAccount> accounts, int accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }
}