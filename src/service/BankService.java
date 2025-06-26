package service;

import interfaces.Transactable;
import model.Account;
import model.CurrentAccount;
import model.SavingAccount;
import utils.FileService;

import java.io.*;
import java.util.*;

public class BankService implements Transactable {
    private static final String ACCOUNT_FILE = "data/accounts.txt";
    private List<Account> accounts;

    public BankService() {
        accounts = loadAccounts();
    }

    //  Create Saving Account
    public Account createSavingAccount(String userId) {
        String accNo = "ACC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Account account = new SavingAccount(accNo, userId, 0.0);
        accounts.add(account);
        FileService.saveAccount(account, null);
        System.out.println("✅ Savings Account created: " + accNo);
        return account;
    }

    // Create Current Account
    public Account createCurrentAccount(String userId) {
        String accNo = "ACC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Account account = new CurrentAccount(accNo, userId, 0.0);
        accounts.add(account);
        FileService.saveAccount(account, null);
        System.out.println("✅ Current Account created: " + accNo);
        return account;
    }

    // Deposit
    @Override
    public void deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            account.deposit(amount);
            TransactionService.recordDeposit(accountNumber, amount);
            System.out.println("✅ Deposited ₹" + amount);
            saveAll();
        } else {
            System.out.println("❌ Account not found.");
        }
    }

    //  Withdraw
    @Override
    public void withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null) {
            if (account.withdraw(amount)) {
                TransactionService.recordWithdraw(accountNumber, amount);
                System.out.println("✅ Withdrawn ₹" + amount);
                saveAll();
            } else {
                System.out.println("❌ Insufficient balance.");
            }
        } else {
            System.out.println("❌ Account not found.");
        }
    }

    //  Transfer
    @Override
    public void transfer(String fromAccNo, String toAccNo, double amount) {
        Account from = findAccount(fromAccNo);
        Account to = findAccount(toAccNo);

        if (from == null || to == null) {
            System.out.println("❌ One or both accounts not found.");
            return;
        }

        if (from.withdraw(amount)) {
            to.deposit(amount);
            TransactionService.recordTransfer(fromAccNo, toAccNo, amount);
            System.out.println("✅ ₹" + amount + " transferred successfully.");
            saveAll();
        } else {
            System.out.println("❌ Insufficient balance in sender account.");
        }
    }

    //  Find Account
    private Account findAccount(String accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accNo)) return acc;
        }
        return null;
    }

    // Load Accounts
    private List<Account> loadAccounts() {
        List<Account> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String type = parts[0];  // SAV or CUR
                    String accNo = parts[1];
                    String userId = parts[2];
                    double bal = Double.parseDouble(parts[3]);

                    Account acc;
                    if (type.equals("SAV")) {
                        acc = new SavingAccount(accNo, userId, bal);
                    } else {
                        acc = new CurrentAccount(accNo, userId, bal);
                    }
                    list.add(acc);
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ Could not load accounts: " + e.getMessage());
        }
        return list;
    }

    // Save All Accounts
    private void saveAll() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE))) {
            for (Account acc : accounts) {
                String type = acc instanceof SavingAccount ? "SAV" : "CUR";
                writer.write(type + "," + acc.getAccountNumber() + "," + acc.getUserID() + "," + acc.getBalance());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Failed to save accounts: " + e.getMessage());
        }
    }

    // View balance 
    public void viewBalance(String accountNumber) {
    Account account = findAccount(accountNumber);
    if (account != null) {
        System.out.println("💰 Current Balance: ₹" + account.getBalance());
    } else {
        System.out.println("❌ Account not found.");
    }
}

}
