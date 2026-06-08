package lk.jiat.edu.server;

import BankingApp.AccountPOA;
import BankingApp.InsufficientBalance;

import java.util.HashMap;

public class AccountImpl extends AccountPOA {

    private HashMap<String, Double> db = new HashMap<>();

    public AccountImpl() {
        db.put("ACC001", 1000.00);
        db.put("ACC002", 2000.00);
        db.put("ACC003", 3000.00);
    }

    @Override
    public double getBalance(String accNo) {
        return db.getOrDefault(accNo, 0.0);
    }

    @Override
    public void deposit(String accNo, double amount) {
        double currBalance = getBalance(accNo);
        db.put(accNo, currBalance + amount);

        System.out.println("Server log: Deposited " + accNo + " LKR " + currBalance + "\n" + "successfully");
    }

    @Override
    public void withdraw(String accNo, double amount) throws InsufficientBalance {
        double currentBalance = getBalance(accNo);
        if (currentBalance < amount) {
            System.out.println("Server log: Withdrawal failed due to insufficient balance");
            throw new InsufficientBalance("Insufficient Balance. Please check your account balance");
        } else {
            db.put(accNo, currentBalance - amount);
            System.out.println("Server log: Transaction successful");
        }
    }
}
