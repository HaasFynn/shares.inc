package investment_types;

import archive.Price;
import archive.Transaction;

public class Share extends Investment {

    String company;
    double companyValue;

    public Share() {
        this.name = getName();
        this.shortl = getShortl();
        this.company = getCompanyName();
        this.stockReturn = getStockReturn();
        this.existingSharesAmount = getExistingSharesAmount();
        this.dateOfEntry = getDateOfEntry();
        this.transactions = getTransactions();
        this.pricePerShare = getPricePerShare();
        this.companyValue = getCompanyValue();
    }

    private double getCompanyValue() {
        return 0;
    }

    private String getCompanyName() {
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
    String getDateOfEntry() {
        return null;
    }

    @Override
    double getStockReturn() {
        return 0;
    }

    @Override
    Transaction[] getTransactions() {
        return null;
    }

    @Override
    Price getPricePerShare() {
        return null;
    }
}
