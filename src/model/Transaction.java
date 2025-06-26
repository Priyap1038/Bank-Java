package model;

public class Transaction {
    private String txnId;
    private String type;          // DEPOSIT, WITHDRAW, TRANSFER
    private String fromAcc;
    private String toAcc;
    private double amount;

    public Transaction(String txnId, String type, String fromAcc, String toAcc, double amount) {
        this.txnId = txnId;
        this.type = type;
        this.fromAcc = fromAcc;
        this.toAcc = toAcc;
        this.amount = amount;
    }

    public String getTxnId() {
        return txnId;
    }

    public String getType() {
        return type;
    }

    public String getFromAcc() {
        return fromAcc;
    }

    public String getToAcc() {
        return toAcc;
    }

    public double getAmount() {
        return amount;
    }
}
