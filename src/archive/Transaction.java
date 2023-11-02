package archive;

import game.User;

import java.util.Date;

public class Transaction {

    public Date date;
    public User sender;
    public User receiver;
    public String tradedInvestment;
    public int amount;
    public double transferedMoney;

    public Transaction() {
        this.date = new Date();
    }
    public String toString() {
        String splitLine = "\n----------------------------------\n";
        return (splitLine + "Datum der Transaktion: " + date + "\n" + "Sender: " + sender.firstName + " " + sender.lastName + "\n" + "Empfänger: " + receiver.firstName + " " + receiver.lastName + splitLine + tradedInvestment + "\n" + "Anzahl: " + amount + splitLine + "Übertragener Betrag: " + transferedMoney + splitLine);
    }
}
