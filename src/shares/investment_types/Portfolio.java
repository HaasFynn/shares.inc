package shares.investment_types;


import java.util.ArrayList;

public class Portfolio {
    public ArrayList<Share> shares;
    public ArrayList<Resource> resources;
    public ArrayList<Crypto> crypto;
    public ArrayList<ETF> ETFs;

    public Portfolio() {
        this.shares = new ArrayList<>();
        this.resources = new ArrayList<>();
        this.crypto = new ArrayList<>();
        this.ETFs = new ArrayList<>();
    }


}
