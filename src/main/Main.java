package main;

import archive.Transaction;
import function.Operator;
import function.Reader;
import game.User;
import investment_types.Crypto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader.toBinaryFile binaryFile = new Reader.toBinaryFile();
        start();
        //start();
    }

    private static void start() throws IOException {
        Connection con = connectToSQL();
        Operator operator = new Operator(con);
        while (true) {
            boolean loginSucceed;
            do {
                loginSucceed = operator.loginOperator();
            } while (!loginSucceed);
            boolean backToLogin;
            do {
                backToLogin = operator.menuOperator();
            } while (!backToLogin);
        }
    }

    private static Connection connectToSQL() {
        String url = "jdbc:mysql://localhost:3306/phpmyadmin"; //jdbc:mysql://localhost:3306/phpmyadmin
        String user = "root";
        String pass = "";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Verbindung erfolgreich hergestellt");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return con;
    }

    private static void testTransaction() throws IOException {
        Transaction transaction = new Transaction();
        Crypto bitcoin = new Crypto();
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