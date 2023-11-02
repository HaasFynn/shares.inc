package archive;

import java.util.Date;
import java.util.HashMap;

public class Price {

    double currentPrice;
    HashMap<Date, Double> pricesOverTime;
    double highestPrice;
    double lowestPrice;

    public Price() {
        this.currentPrice = getCurrentPrice(currentPrice);
        this.pricesOverTime = getPricesOfAll(pricesOverTime);
        this.highestPrice = getHighestPrice(highestPrice);
        this.lowestPrice = getLowestPrice(lowestPrice);
    }

    private HashMap<Date, Double> getPricesOfAll(HashMap<Date, Double> prices) {
        return prices;
    }

    private double getCurrentPrice(double currentPrice) {
        return currentPrice;
    }
    private double getHighestPrice(double highestPrice) {
        return highestPrice;
    }
    private double getLowestPrice(double lowestPrice) {
        return lowestPrice;
    }
}
