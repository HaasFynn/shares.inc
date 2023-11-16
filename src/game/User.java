package game;

import archive.Transaction;
import investment_types.Portfolio;
import function.Reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class User {

    public String userID;
    public String username;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public double accountBalance;
    public ArrayList<Transaction> userTransactionList;
    public Portfolio userPortfolio;

    public User() throws IOException {
        this.userID = getNewUserID();
        this.username = "";
        this.firstName = Reader.getStringAnswer("Vorname:");
        this.lastName = Reader.getStringAnswer("Nachname:");
        this.email = Reader.getValidEmailAnswer("E-Mail:");
        this.password = Reader.getValidPassword();
        this.accountBalance = getAccountBalance(accountBalance);
        this.userTransactionList = getUserTransactionList();
        this.userPortfolio = getUserPortfolio();
    }

    private String getNewUserID() {
        return UUID.randomUUID().toString();
    }

    private Portfolio getUserPortfolio() {
        return new Portfolio();
    }

    private ArrayList<Transaction> getUserTransactionList() {
        return new ArrayList<>();
    }


    private double getAccountBalance(double accountBalance) {
        return accountBalance;
    }

    public String toString() {
        return (this.username + "\n" + this.firstName + " " + this.lastName + "\n" + this.accountBalance);
    }
}
