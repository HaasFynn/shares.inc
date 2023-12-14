package shares.archive;

import shares.function.Operator;
import shares.function.Print;
import shares.function.SQL;

import java.sql.Date;
import java.util.HashMap;

public class Price {
    public double currentPrice;
    HashMap<Date, Double> pricesOverTime;

    public Price(Date date, int currentPrice) {
        this.pricesOverTime = SQL.GetFromDB.getPricesOverTime();
        this.currentPrice = getCurrentPrice();

    }

    public HashMap<Date, Double> getPricesOfAll(HashMap<Date, Double> prices) {
        return prices;
    }

    private double getCurrentPrice() {
        if (this.pricesOverTime != null) {
            return this.pricesOverTime.get(new Date(Operator.getMillis()));
        } else {
            Print.printError("\"currentPrice\" konnte nicht bestimmt werden, da \"pricesOverTime\" null ist!");
        }
        return 0;
    }

    private double getHighestPrice() {
        return pricesOverTime.values().stream().max(Double::compareTo).orElse(0.0);
    }

    private double getLowestPrice() {
        return pricesOverTime.values().stream().min(Double::compareTo).orElse(0.0);
    }
}
