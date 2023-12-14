package shares.investment_types;


import java.sql.Date;

public class Resource extends Investment {

    public Resource() {
        this.name = getName();
        this.shortl = getShortl();
        this.stockReturnInPercent = getStockReturnInPercent();
        this.existingSharesAmount = getExistingSharesAmount();
        this.dateOfEntry = getDateOfEntry();
        this.pricePerShare = 0;
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
        return "Name: " + this.name + "\n Shortl: " + this.shortl + "\n Dividende: " + this.stockReturnInPercent + "%\n Anzahl verfügbare Anteile: " + existingSharesAmount + " Stück \n Datum: " + this.dateOfEntry + "\n Preis pro Aktie: " + pricePerShare + ".-";
    }
}
