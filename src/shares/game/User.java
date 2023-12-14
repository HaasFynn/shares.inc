package shares.game;

import shares.archive.Transaction;
import shares.investment_types.Portfolio;

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

    public User() {
        this.userID = getNewUserID();
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.accountBalance = 0.0;
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

    public String toString() {
        return (this.username + "\n" + this.firstName + " " + this.lastName + "\n" + this.accountBalance);
    }
}
