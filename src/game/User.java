package game;

import archive.Transaction;
import investment_types.Portfolio;
import function.Reader;

import java.io.IOException;

public class User {

    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public int accountBalance;
    public Transaction[] userTransactionList;
    public Portfolio userPortfolio;

    public User() throws IOException {
        this.firstName = Reader.getStringAnswer("Vorname:");
        this.lastName = Reader.getStringAnswer("Nachname:");
        this.email = Reader.getValidEmailAnswer("E-Mail:");
        this.password = Reader.getStringAnswer("Passwort");
        this.accountBalance = getAccountBalance(accountBalance);
    }


    private int getAccountBalance(int accountBalance) {
        return accountBalance;
    }

}
