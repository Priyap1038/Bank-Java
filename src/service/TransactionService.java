package service;

import model.Transaction;
import utils.FileService;

import java.util.UUID;

public class TransactionService {

    public static void recordDeposit(String toAcc, double amount) {
        String txnId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Transaction txn = new Transaction(txnId, "DEPOSIT", "-", toAcc, amount);
        FileService.saveTransaction(txn);
    }

    public static void recordWithdraw(String fromAcc, double amount) {
        String txnId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Transaction txn = new Transaction(txnId, "WITHDRAW", fromAcc, "-", amount);
        FileService.saveTransaction(txn);
    }

    public static void recordTransfer(String fromAcc, String toAcc, double amount) {
        String txnId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Transaction txn = new Transaction(txnId, "TRANSFER", fromAcc, toAcc, amount);
        FileService.saveTransaction(txn);
    }
}
