package service;

import model.Account;
import java.util.List;

public abstract class BaseService {
    protected abstract List<Account> loadAccounts();

    protected Account findAccount(List<Account> accounts, String accNo) {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accNo)) return acc;
        }
        return null;
    }

    protected void printSuccess(String msg) {
        System.out.println("✅ " + msg);
    }

    protected void printError(String msg) {
        System.out.println("❌ " + msg);
    }
}
