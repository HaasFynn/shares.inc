package investment_types;

import archive.Price;
import archive.Transaction;

public class CryptoCurrency extends Investment{
    public CryptoCurrency() {
        this.name = getName();
        this.shortl = getShortl();
        this.existingSharesAmount = getExistingSharesAmount();
        this.dateOfEntry = getDateOfEntry();
        this.transactions = getTransactions();
        this.pricePerShare = getPricePerShare();
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
