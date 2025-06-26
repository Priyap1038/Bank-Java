package model;

public abstract class Account {
    private String accountNumber;
    private String userID;
    protected double balance;

    public Account(String accountNumber, String userId, double balance) {
        this.accountNumber = accountNumber;
        this.userID = userId;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getUserID() {
        return userID;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void deposit(double amount);

    public abstract boolean withdraw(double amount);

    public void transfer(Account to, double amount){
        if(amount > balance){
            System.out.println("Insufficient balance to trnasfer");
        }else{
            this.withdraw(amount);
            to.deposit(amount);
            System.out.println("Trnsfer successful.");
        }
    }

}
