package game;

import investment_types.*;

import java.sql.Connection;

public class InvestmentMarket {

    Share[] shareList;
    Resource[] resourceList;
    Crypto[] cryptoList;
    ETF[] ETFList;
    String[] shareNameList;
    String[] resourceNameList;
    String[] ETFNameList;
    String[] cryptoNameList;
    Connection db;

    public InvestmentMarket(Connection db) {
        this.db = db;
        createLists();
        setNamesLists();
    }

    private void createLists() {
        this.shareList = new Share[10];
        this.resourceList = new Resource[4];
        this.cryptoList = new Crypto[3];
        this.ETFList = new ETF[3];
    }

    private void setNamesLists() {
        this.shareNameList = new String[]{"Celestial Innovacorp", "QuantumSynth Enterprises", "GenoStellar Labs", "LuminaTech Ventures", "SunFusion Dynamics", "CyberGuardian Systems", "RoboFusion Innovations", "BioNovaGenix Solutions", "Earthbound Minerals", "Co.SkyWings", "Transport Technologies"};
        this.resourceNameList = new String[]{"Gold", "Öl", "Kupfer", "Eisen"};
        this.ETFNameList = new String[]{"MSCI World", "MSCI US", "ETC Group Physical Bitcoin"};
        this.cryptoNameList = new String[]{"Bitcoin", "Ethereum", "Dogecoin"};
        giveNames();
    }

    private void giveNames() {
        setShares();
        setResources();
        setETFs();
        setCrypto();
    }

    private void setShares() {
        int nextName = 0;
        for (Share share : shareList) {
            share.name = setInformationList(nextName, shareNameList);
            nextName++;
        }

    }

    public String setInformationList(int nextName, String[] list) {
        return list[nextName];
    }

    private void setResources() {
        int nextName = 0;
        for (Resource resource : resourceList) {
            resource.name = setInformationList(nextName, resourceNameList);
            nextName++;
        }
    }

    private void setCrypto() {
        int nextName = 0;
        for (Crypto crypto : cryptoList) {
            crypto.name = setInformationList(nextName, cryptoNameList);
            nextName++;
        }
    }

    private void setETFs() {
        int nextName = 0;
        for (ETF etf : ETFList) {
            etf.name = setInformationList(nextName, ETFNameList);
            nextName++;
        }
    }
}
