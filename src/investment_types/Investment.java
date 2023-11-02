package investment_types;

import archive.Price;
import archive.Transaction;

public abstract class Investment {

    public String name;
    public String shortl;
    public double stockReturn;
    public int existingSharesAmount;
    public String dateOfEntry;
    public Transaction[] transactions;
    public Price pricePerShare;

    abstract String getName();
    abstract String getShortl();
    abstract int getExistingSharesAmount();
    abstract String getDateOfEntry();
    abstract double getStockReturn();
    abstract Transaction[] getTransactions();
    abstract Price getPricePerShare();
}
