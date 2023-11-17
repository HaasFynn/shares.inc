package function;

import game.User;
import investment_types.Crypto;
import investment_types.ETF;
import investment_types.Resource;
import investment_types.Share;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;


public class Operator {
    Share[] shares;
    Resource[] resources;
    ETF[] etfs;
    Crypto[] cryptos;
    static User loggedInUser;
    static HashMap<String, User> userList = getUserList();

    public Operator() {
        this.shares = new Share[10];
        this.resources = new Resource[4];
        this.etfs = new ETF[3];
        this.cryptos = new Crypto[3];
    }

    private static HashMap<String, User> getUserList() {
        HashMap<String, User> userList = new HashMap<>();
        int numberOfDatasets = SQL.getLastDataset();
        for (int i = 1; i < numberOfDatasets; i++) {
            User user = SQL.getUserFromDB(i);
            userList.put(user.userID, user);
        }
        return userList;
    }

    public static User getUserInformation(User user) throws IOException {
        user.username = Reader.getStringAnswer("Benutzername:");
        user.firstName = Reader.getStringAnswer("Vorname:");
        user.lastName = Reader.getStringAnswer("Nachname:");
        user.email = Reader.getValidEmailAnswer("E-mail:");
        user.password = Reader.getValidPassword();
        return user;
    }


    public boolean loginOperator() throws IOException {
        Print.printLoginMenu();
        switch (Reader.getIntAnswer("Möchten sie sich einloggen oder registrieren?")) {
            case 1:
                return login();
            case 2:
                register();
                break;
            default:
                System.out.println("Diese Funktion existiert nicht!");
        }
        return false;
    }


    public boolean menuOperator() throws IOException {
        Print.printMenu();
        switch (Reader.getIntAnswer("Wählen sie eine Funktion")) {
            case 1:
                investOperator();
                break;
            case 2:
                showInvestOperator();
                break;
            case 3:
                Print.printUserPortfolio(loggedInUser);
                break;
            case 4:
                Print.printUserAccountBalance(loggedInUser);
                break;
            case 5:
                userAccountManagement();
                break;
            case 6:
                payOutMoney();
                break;
            case 7:
                logOut();
                return true;
            default:
                Print.printError("Diese Funktion existiert nicht!");
        }
        return false;
    }


    public void userAccountManagement() throws IOException {
        Print.printUserAccountManagementList();
        switch (Reader.getIntAnswer("Wählen sie eine Funktion")) {
            case 1:
                setNewEmail();
                break;
            case 2:
                setNewPassword();
                break;
            case 3:
                setNewUsername();
                break;
            case 4:
                deleteAccount();
                break;
            default:
                Print.printError("Diese Funktion existiert nicht!");
        }
    }


    private void showInvestOperator() {
        Print.printInvestOccasions();
        String[] investmentNames;
        String investmentType = "";
        try {
            switch (Reader.getIntAnswer("Welche Investitionsmöglichkeit möchtest du sehen?")) {
                case 1:
                    investmentNames = new String[shares.length];
                    IntStream.range(0, shares.length).forEach(i -> investmentNames[i] = shares[i].name);
                    break;
                case 2:
                    investmentNames = new String[resources.length];
                    IntStream.range(0, resources.length).forEach(i -> investmentNames[i] = resources[i].name);
                    break;
                case 3:
                    investmentNames = new String[etfs.length];
                    IntStream.range(0, etfs.length).forEach(i -> investmentNames[i] = etfs[i].name);
                    break;
                case 4:
                    investmentNames = new String[cryptos.length];
                    IntStream.range(0, cryptos.length).forEach(i -> investmentNames[i] = cryptos[i].name);
                    break;
                default:
                    System.out.println("Diese Art von Investitionsmöglichkeit existiert nicht!");
                    investmentNames = null;
                    break;
            }
            if (investmentNames != null) {
                Print.printSelectedInvestmentList(investmentType, investmentNames);
            }
        } catch (Exception e) {
            Print.printError("Keine Einträge vorhanden!");
        }
    }

    private void investOperator() throws IOException {
        Print.printInvestOccasions();
        switch (Reader.getIntAnswer("In was möchtest du investieren?")) {
            case 1:
                buyShares();
        }

    }

    private void buyShares() throws IOException {
        String shareNameInput = Reader.getStringAnswer("Aktienname:");
        Share currentShare = getShare(shareNameInput);
        if (currentShare != null) {
            double amountOfMoney = Reader.getDoubleAnswer("Wie viel Geld möchtest du investieren?");
            if (hasEnoughMoney(amountOfMoney)) {
                loggedInUser.accountBalance -= amountOfMoney;
                int amountOfShares = getAmountOfShares(amountOfMoney, currentShare);
                currentShare.existingSharesAmount -= amountOfShares;
                loggedInUser.userPortfolio.shares.add(currentShare);
            }
        }
    }

    private int getAmountOfShares(double amountOfMoney, Share currentShare) {
        amountOfMoney /= currentShare.pricePerShare.currentPrice;
        return (int) amountOfMoney;
    }

    private Share getShare(String shareNameInput) {
        for (Share share : shares) {
            if (shareNameInput.equals(share.name)) {
                return share;
            }
        }
        Print.printError("Aktie konnte nicht gefunden werden!");
        return null;
    }

    private void deleteAccount() throws IOException {
        if (isUserAuthorizedToDeleteAcc()) {
            userList.remove(loggedInUser.userID);
        }
        SQL.deleteUser(loggedInUser.userID);
    }

    private boolean isUserAuthorizedToDeleteAcc() throws IOException {
        String passwordInput = Reader.getStringAnswer("Geben sie ihr Passwort ein:");
        if (passwordInput.equals(loggedInUser.password)) {
            return Reader.getBooleanAnswer("Sind sie sicher, dass sie ihren Account löschen möchten? (Diese Aktion ist nicht rückgängig machbar!)");
        } else {
            Print.printError("Passwort ist falsch!");
        }
        return false;
    }

    private void setNewEmail() throws IOException {
        String newEmail = Reader.getValidEmailAnswer("Geben sie eine neue E-Mail ein:");
        String verifyEmail = Reader.getValidEmailAnswer("Geben sie die E-Mail erneut ein:");
        String password = Reader.getStringAnswer("Geben sie ihr Passwort ein:");
        if (doStringsEqual(newEmail, verifyEmail) && isEmailFree(newEmail)) {
            if (password.equals(loggedInUser.password)) {
                loggedInUser.email = newEmail;
                System.out.println("Änderung erfolgreich!");

            } else {
                Print.printError("Das Passwort ist inkorrekt!");
            }
        } else {
            Print.printError("Email stimmt nicht überein!");
        }
    }

    static boolean isEmailFree(String emailInput) {
        for (Map.Entry<String, User> entry : userList.entrySet()) {
            User user = entry.getValue();
            if (emailInput.equalsIgnoreCase(user.email)) {
                System.out.println("Diese E-mail wird bereits verwendet!");
                return false;
            }
        }
        return true;
    }

    private void setNewPassword() throws IOException {
        String newPassword = Reader.getStringAnswer("Geben sie ein neues Passwort ein:");
        String verifyPassword = Reader.getStringAnswer("Wiederholen sie ihr neues Passwort:");
        String oldPassword = Reader.getStringAnswer("Geben sie ihr altes Passwort ein:");
        if (doStringsEqual(newPassword, verifyPassword)) {
            if (oldPassword.equals(loggedInUser.password)) {
                loggedInUser.password = newPassword;
                System.out.println("Änderung erfolgreich!");
            } else {
                Print.printError("Das alte Passwort ist falsch!");
            }
        } else {
            Print.printError("Das neue Passwort stimmt nicht überein!");
        }
    }

    private void setNewUsername() throws IOException {
        String newUsername = Reader.getStringAnswer("Geben sie einen neuen Username ein:");
        String verifyUsername = Reader.getStringAnswer("Wiederholen sie ihren Username:");
        String password = Reader.getStringAnswer("Geben sie ihr Passwort ein:");
        if (doStringsEqual(newUsername, verifyUsername)) {
            if (password.equals(loggedInUser.password)) {
                loggedInUser.username = newUsername;
                System.out.println("Änderung erfolgreich!");
            } else {
                Print.printError("Das Passwort ist inkorrekt!");
            }
        } else {
            Print.printError("Der neue Username stimmt nicht überein!");
        }
    }

    private boolean doStringsEqual(String input, String input1) {
        return input.equalsIgnoreCase(input1);
    }

    private void logOut() {
        loggedInUser = null;
    }

    private static void payOutMoney() throws IOException {
        double amount = Reader.getIntAnswer("Wie viel Geld möchten sie auszahlen?");
        if (hasEnoughMoney(amount)) {
            loggedInUser.accountBalance -= amount;
            System.out.println("Geld erfolgreich ausgezahlt!");
        } else {
            Print.printError("Du besitzt nicht so viel Geld!");
        }
    }

    private static boolean hasEnoughMoney(double amount) {
        return amount < loggedInUser.accountBalance;
    }

    private boolean login() throws IOException {
        String emailOrUsername = Reader.getStringAnswer("E-Mail / Benutzername:");
        String password = Reader.getStringAnswer("Passwort:");
        for (Map.Entry<String, User> entry : userList.entrySet()) {
            User user = entry.getValue();
            if ((emailOrUsername.equalsIgnoreCase(user.email) || emailOrUsername.equalsIgnoreCase(user.username)) && password.equalsIgnoreCase(user.password)) {
                Print.loginSucceed(user);
                loggedInUser = user;
                return true;
            } else {
                System.out.println("Keinen Benutzer mit diesem Benutzernamen oder Passwort gefunden...");
                try {
                    loginOperator();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        Print.printError("Keinen Benutzer mit diesem Benutzernamen oder Passwort gefunden...");
        return false;
    }

    private void register() throws IOException {
        User newUser = new User();
        userList.put(newUser.userID, newUser);
        String usernameInput = Reader.getStringAnswer("Username:");
        while (!isUsernameFree(usernameInput)) {
            Print.printError("Username wird bereits verwendet!");
            usernameInput = Reader.getStringAnswer("Geben sie einen anderen Username ein:");
        }
        newUser.username = usernameInput;
        SQL.addUserToDB(newUser);
        System.out.println("Erfolgreich registriert!");
    }

    private boolean isUsernameFree(String usernameInput) {
        for (Map.Entry<String, User> entry : userList.entrySet()) {
            User user = entry.getValue();
            if (usernameInput.equals(user.username)) {
                return false;
            }
        }
        return true;
    }
}
