package shares.investment_types;

import java.sql.Date;

public abstract class Investment {

    public String name;
    public String shortl;
    public double stockReturnInPercent;
    public int existingSharesAmount;
    public Date dateOfEntry;
    public double pricePerShare;

    abstract String getName();
    abstract String getShortl();
    abstract int getExistingSharesAmount();
    abstract Date getDateOfEntry();
    abstract double getStockReturnInPercent();
}
