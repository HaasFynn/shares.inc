package function;

import game.User;
import investment_types.Share;
public class Print {

    public static void printStartMenu() {
        System.out.println("----------------");
        System.out.println("Menü:");
        System.out.println("[1] Einloggen");
        System.out.println("[2] Registrieren");
        System.out.println("----------------");
    }

    public static void printUser(User user) {
        System.out.println(user.firstName + " " + user.lastName + "\n" + user.accountBalance);
    }

    public static void printMenu() {
        System.out.println("----------------");
        System.out.println("Menü:");
        System.out.println("[1] Investieren");
        System.out.println("[2] Aktienmarkt einsehen");
        System.out.println("[3] Momentanes Portfolio einsehen");
        System.out.println("[4] Kontostand einsehen");
        System.out.println("[5] Account verwalten");
        System.out.println("[6] Geld auszahlen");
        System.out.println("----------------");
    }
    public static void printAccountManagementList() {
        System.out.println("----------------");
        System.out.println("[1] E-Mail ändern");
        System.out.println("[2] Passwort ändern");
        System.out.println("[3] Passwort ändern");
        System.out.println("[4] Account löschen");
        System.out.println("----------------");
    }

    public static void printInvestOccasions() {
        System.out.println("----------------");
        System.out.println("Liste aller Investitionsarten:");
        System.out.println("[1] Aktien");
        System.out.println("[2] Rohstoffe");
        System.out.println("[3] Crypto");
        System.out.println("[4] ETF's");
        System.out.println("----------------");
    }

    public static void printCurrentInvestmentlist(String currentInvestment, Share[] shareNames) {
        System.out.println("----------------");
        System.out.println(currentInvestment);
        for (Share share : shareNames) {
            System.out.println("Name: " + share.name);
            System.out.println("Preis pro Anteil: " + share.pricePerShare);
        }
        System.out.println("----------------");
    }

    public static void printError(String errorText) {
        System.out.println("----------------");
        System.out.println(errorText);
        System.out.println("----------------");
    }

}
