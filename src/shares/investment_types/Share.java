package shares.investment_types;

import java.sql.Date;

public class Share extends Investment {

    public String company;
    public double companyValue;

    public Share() {
        this.name = getName();
        this.shortl = getShortl();
        this.company = "";
        this.stockReturnInPercent = getStockReturnInPercent();
        this.existingSharesAmount = getExistingSharesAmount();
        this.dateOfEntry = getDateOfEntry();
        this.pricePerShare = 0;
        this.companyValue = getCompanyValue();
    }

    private double getCompanyValue() {
        return 0;
    }

    @Override
    String getName() {
        return "";
    }

    @Override
    String getShortl() {
        return "";
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
        return " Name: " + this.name + "\n Shortl: " + this.shortl + "\n Unternehmen: " + this.company + " Stück \n Dividende: " + this.stockReturnInPercent + "%\n Anzahl verfügbare Aktien: " + this.existingSharesAmount + " Stück \n Eintrittsdatum: " + this.dateOfEntry + "\n Preis pro Aktie: " + this.pricePerShare + ".-\n Unternehmenswert: " + this.companyValue + ".-";
    }
}
