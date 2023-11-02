package main;

import archive.Transaction;
import function.Reader;
import game.User;
import investment_types.CryptoCurrency;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        testTransaction();
        //startGame();
    }

    private static void startGame() throws IOException {

        Connection db = connectToSQL();
        boolean userContinues;
        do {
            userContinues = Reader.getBooleanAnswer("Möchtest du noch eine Runde spielen?");
        } while (userContinues);
    }

    private static Connection connectToSQL() {
        Connection c = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:sharesDB.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
        return c;
    }

    private static void testTransaction() throws IOException {
        Transaction transaction = new Transaction();
        CryptoCurrency bitcoin = new CryptoCurrency();
        bitcoin.name = "bitcoin";
        transaction.date = new Date();
        transaction.sender = new User();
        transaction.receiver = new User();
        transaction.tradedInvestment = bitcoin.name;
        transaction.transferedMoney = Math.random();
        transaction.amount = 4;
        System.out.println(transaction);
    }

}