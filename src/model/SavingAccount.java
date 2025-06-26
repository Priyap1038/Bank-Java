package model;

public class SavingAccount extends Account {
    public SavingAccount(String accountNumber, String userId, double balance) {
        super(accountNumber, userId, balance);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) balance += amount;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
