package utils;

import model.User;
import model.Account;
import model.Transaction;

import java.io.*;

public class FileService {

    public static void saveUser(User user) {
        try {
            FileWriter writer = new FileWriter("data/users.txt", true);
            writer.write("UserId : "+user.getUserID() + "," +"UserName : "+ user.getName() + "," + "UserEmail : "+user.getEmail() + "," + "Password : "+user.getPassword() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }

    public static void saveAccount(Account account, User user) {
        try {
            FileWriter writer = new FileWriter("data/accounts.txt", true);
            writer.write("UserName : "+ user.getName()+","+"Account no: "+account.getAccountNumber() + "," +"UserId : "+ account.getUserID() + "," +"Balance amount: "+ account.getBalance() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving account: " + e.getMessage());
        }
    }

     public static void saveTransaction(Transaction txn) {
        try (FileWriter writer = new FileWriter("data/transactions.txt", true)) {
            writer.write("UserId : "+txn.getTxnId() + "," + txn.getType() + "," + txn.getFromAcc() + "," + txn.getToAcc() + "," + txn.getAmount() + "\n");
        } catch (IOException e) {
            System.out.println("❌ Error saving transaction: " + e.getMessage());
        }
    }
}
