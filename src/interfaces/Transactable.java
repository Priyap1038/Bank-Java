package interfaces;

// import model.Account;

public interface Transactable {
    void deposit(String accNo, double amount);
    void withdraw(String accNo, double amount);
    void transfer(String fromAccNo, String toAccNo, double amount);
}
