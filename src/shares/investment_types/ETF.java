package shares.investment_types;


import java.sql.Date;
import java.util.ArrayList;

public class ETF extends Investment {

    public ArrayList<Share> shareList;
    public ETF() {
        this.name = getName();
        this.stockReturnInPercent = getStockReturnInPercent();
        this.dateOfEntry = getDateOfEntry();
        this.existingSharesAmount = getExistingSharesAmount();
        this.pricePerShare = 0;
        this.shareList = getShareList();
    }

    private ArrayList<Share> getShareList() {
        return null;
    }

    @Override
    String getName() {
        return null;
    }

    @Override
    String getShortl() {
        return null;
    }

    @Override
    int getExistingSharesAmount() {
        return 0;
    }

    @Override
    Date getDateOfEntry() {
        return null;
    }

    @Override
    double getStockReturnInPercent() {
        return 0;
    }


    public String toString() {
        return " Name: " + this.name + "\n Dividende: " + this.stockReturnInPercent + "%\n Anzahl verfügbare Aktien: " + this.existingSharesAmount + " Stück \n Eintrittsdatum: " + this.dateOfEntry + "\n Preis pro Aktie: " + this.pricePerShare;
    }
}
