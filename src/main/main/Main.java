package main;

import model.User;
import service.AuthService;
import service.BankService;

import java.util.Scanner;
import java.io.File;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BankService bankService = new BankService();
    private static User currentUser = null;

    public static void main(String[] args) {
        setupDataFiles();

        System.out.println("====== Welcome to MiniBank ======");

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 1)
                register();
            else if (choice == 2)
                login();
            else
                break;
        }

        System.out.println("✅ Thank you for using MiniBank!");
    }

    private static void register() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();

        currentUser = AuthService.register(name, email, pass);
        currentUser = null;
        System.out.println("👉 Please login now to continue.");
    }

    private static void login() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String pass = scanner.nextLine();

        currentUser = AuthService.login(email, pass);
        if (currentUser != null)
            showDashboard();
    }

    private static void showDashboard() {
        while (true) {
            System.out.println("\n==== Dashboard ====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Balance");
            System.out.println("6. Logout");
            System.out.print("Select: ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            if (opt == 1) {
                System.out.println("1. Saving Account");
                System.out.println("2. Current Account");
                System.out.print("Choose Account Type: ");
                int accType = scanner.nextInt();
                scanner.nextLine();

                if (accType == 1) {
                    bankService.createSavingAccount(currentUser.getUserID());
                } else if (accType == 2) {
                    bankService.createCurrentAccount(currentUser.getUserID());
                } else {
                    System.out.println("❌ Invalid account type.");
                }
            } else if (opt == 2) {
                System.out.print("Account Number: ");
                String acc = scanner.nextLine();
                System.out.print("Amount: ");
                double amt = scanner.nextDouble();
                scanner.nextLine();
                bankService.deposit(acc, amt);
            } else if (opt == 3) {
                System.out.print("Account Number: ");
                String acc = scanner.nextLine();
                System.out.print("Amount: ");
                double amt = scanner.nextDouble();
                scanner.nextLine();
                bankService.withdraw(acc, amt);
            } else if (opt == 4) {
                System.out.print("From Account: ");
                String from = scanner.nextLine();
                System.out.print("To Account: ");
                String to = scanner.nextLine();
                System.out.print("Amount: ");
                double amt = scanner.nextDouble();
                scanner.nextLine();
                bankService.transfer(from, to, amt);
            } else if (opt == 5) {
    System.out.print("Enter Account Number: ");
    String acc = scanner.nextLine();
    bankService.viewBalance(acc);
}else {
                break;
            }
        }
    }

    private static void setupDataFiles() {
        String[] files = {
                "data/users.txt",
                "data/accounts.txt"
        };

        for (String file : files) {
            try {
                File f = new File(file);
                f.getParentFile().mkdirs(); // Ensure "data/" exists
                f.createNewFile(); // Create if doesn't exist
            } catch (Exception e) {
                System.out.println("⚠️ Could not create " + file);
            }
        }
    }
}
