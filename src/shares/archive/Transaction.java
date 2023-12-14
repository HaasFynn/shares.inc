package shares.archive;

import java.util.Date;

public class Transaction {

    public String transactionID;
    public Date date;
    public String senderID;
    public String receiverID;
    public String Investment;
    public int amount;
    public double transferAmount;

    public Transaction() {
        this.date = new Date();
    }

    public String toString() {
        String splitLine = "\n----------------------------------\n";
        return (splitLine + "TransaktionsID: " + transactionID + "\n" + "Datum der Transaktion: " + date + "\n" + "Sender ID: " + receiverID + "\n" + "Empfänger ID: " + receiverID + splitLine + Investment + "\n" + "Anzahl: " + amount + splitLine + "Übertragener Betrag: " + transferAmount + splitLine);
    }
}
