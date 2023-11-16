package archive;

import java.util.Date;
import java.util.HashMap;

public class Price {
    public double currentPrice;
    HashMap<Date, Double> pricesOverTime;

    public Price(Date currentDate, double currentPrice) {
        this.currentPrice = currentPrice;
        this.pricesOverTime = new HashMap<>();
        this.pricesOverTime.put(currentDate, currentPrice);
    }

    public Price(HashMap<Date, Double> pricesOverTime) {
        this.pricesOverTime = pricesOverTime;
        this.currentPrice = this.pricesOverTime.get(new Date());
    }


    public HashMap<Date, Double> getPricesOfAll(HashMap<Date, Double> prices) {
        return prices;
    }

    private double getCurrentPrice() {
        return currentPrice;
    }

    private double getHighestPrice() {
        return pricesOverTime.values().stream().max(Double::compareTo).orElse(0.0);
    }

    private double getLowestPrice() {
        return pricesOverTime.values().stream().min(Double::compareTo).orElse(0.0);
    }
}
