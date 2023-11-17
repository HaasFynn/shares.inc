package function;

import game.User;
import investment_types.Crypto;
import investment_types.ETF;
import investment_types.Resource;
import investment_types.Share;

import java.sql.SQLException;

public class Print {

    public static void printLoginMenu() {
        System.out.println("----------------");
        System.out.println("Menü:");
        System.out.println("[1] Einloggen");
        System.out.println("[2] Registrieren");
        System.out.println("----------------");
    }

    public static void printMenu() {
        System.out.println("-------------------------------");
        System.out.println("Menü:");
        System.out.println("[1] Investieren");
        System.out.println("[2] Aktienmarkt einsehen");
        System.out.println("[3] Momentanes Portfolio einsehen");
        System.out.println("[4] Kontostand einsehen");
        System.out.println("[5] Account verwalten");
        System.out.println("[6] Geld auszahlen");
        System.out.println("[7] Ausloggen");
        System.out.println("-------------------------------");
    }

    public static void printUserAccountManagementList() {
        System.out.println("-------------------------------");
        System.out.println("[1] E-Mail ändern");
        System.out.println("[2] Passwort ändern");
        System.out.println("[3] Benutzername wechseln");
        System.out.println("[4] Account löschen");
        System.out.println("-------------------------------");
    }

    public static void printInvestOccasions() {
        System.out.println("-------------------------------");
        System.out.println("Liste aller Investitionsarten:");
        System.out.println("[1] Aktien");
        System.out.println("[2] Rohstoffe");
        System.out.println("[3] Crypto");
        System.out.println("[4] ETF's");
        System.out.println("-------------------------------");
    }

    public static void printSelectedInvestmentList(String investmentType, String[] investmentNames) {
        int listNumber = 0;
        System.out.println("-------------------------------");
        System.out.println(investmentType);
        for (String investment : investmentNames) {
            listNumber++;
            System.out.println("[" + listNumber + "] " + investment);
        }
        System.out.println("-------------------------------");
    }

    public static void printCurrentInvestmentlist(String currentInvestment, Share[] shareNames) {
        System.out.println("-------------------------------");
        System.out.println(currentInvestment);
        for (Share share : shareNames) {
            System.out.println("Name: " + share.name);
            System.out.println("Preis pro Anteil: " + share.pricePerShare);
        }
        System.out.println("-------------------------------");
    }

    public static void printError(String errorText) {
        System.err.println("----------------------------------------------------------------------------");
        System.err.println(errorText);
        System.err.println("----------------------------------------------------------------------------");
    }

    static void loginSucceed(User user) {
        System.out.println("Login erfolgreich!");
        System.out.println("-------------------------");
        System.out.println("Willkommen zurück " + user.username);
        System.out.println("-------------------------");
    }

    public static void printUserPortfolio(User user) {
        System.out.println("-------------------------");
        System.out.println("Aktien:");
        for (Share share : user.userPortfolio.shares) {
            System.out.println(share.name + "\n Anzahl Anteile: " + share.userAmountShares);
        }
        System.out.println("-------------------");
        System.out.println("Rohstoffe:");
        for (Resource resource : user.userPortfolio.resources) {
            System.out.println(resource.name + "\n Anzahl Anteile: " + resource.userAmountShares);
        }
        System.out.println("-------------------");
        System.out.println("ETF's");
        for (ETF etf : user.userPortfolio.ETFs) {
            System.out.println(etf.name + "\n Anzahl Anteile: " + etf.userAmountShares);
        }
        System.out.println("-------------------");
        System.out.println("Crypto:");
        for (Crypto crypto : user.userPortfolio.crypto) {
            System.out.println(crypto.name + "\n Anzahl Anteile: " + crypto.userAmountShares);
        }
        System.out.println("-------------------------");

    }

    public static void printUserAccountBalance(User loggedInUser) {
        System.out.println("-------------------------");
        System.out.println("Kontostand: " + loggedInUser.accountBalance);
        System.out.println("-------------------------");
    }

    public static void printPasswordRules() {
        System.out.println("-------------------------");
        System.out.println("- Mindestens 8 Zeichen");
        System.out.println("- Gross- & Kleinbuchstaben");
        System.out.println("- Sonderzeichen");
        System.out.println("-------------------------");
    }

    public static void printAskForPassword() {
        System.out.println("Passwort:");
    }


    public static void customStackPrint(SQLException e) {
        System.out.println();
        System.out.println("..............................................");
        System.out.println();
        e.printStackTrace();
        System.out.println();
        System.out.println("..............................................");
        System.out.println();

    }
}
