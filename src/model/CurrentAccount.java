package model;

public class CurrentAccount extends Account {
    private double overdraftLimit = 5000;

    public CurrentAccount(String accountNumber, String userId, double balance) {
        super(accountNumber, userId, balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance + overdraftLimit) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
