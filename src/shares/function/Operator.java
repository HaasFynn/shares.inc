package shares.function;

import shares.game.User;
import shares.investment_types.Crypto;
import shares.investment_types.ETF;
import shares.investment_types.Resource;
import shares.investment_types.Share;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class Operator {
    ArrayList<Share> shares;
    ArrayList<Resource> resources;
    ArrayList<ETF> etfs;
    ArrayList<Crypto> cryptos;
    static User loggedInUser;
    static HashMap<String, User> userList = getUserList();

    public Operator() {
        this.shares = SQL.GetFromDB.getListOfSharesFromDB();
        this.resources = SQL.GetFromDB.getListOfResourcesFromDB();
        this.etfs = SQL.GetFromDB.getListOfETFsFromDB(new ArrayList<>());
        this.cryptos = SQL.GetFromDB.getListOfCryptoFromDB();
    }

    private static HashMap<String, User> getUserList() {
        HashMap<String, User> userList = new HashMap<>();
        int numberOfDatasets = SQL.GetFromDB.getAmountOfRegisteredSets("shares_main", "userdb");
        int highestRegisteredID = SQL.GetFromDB.getHighestRegisteredID();
        int sizeOfLoop = Math.max(numberOfDatasets, highestRegisteredID);
        for (int i = 1; i <= sizeOfLoop; i++) {
            User user = SQL.GetFromDB.getUserFromDB(i);
            if (user != null) {
                userList.put(user.userID, user);
            }
        }
        return userList;
    }

    public static User getUserInformation(User user) throws IOException {
        user.username = Reader.getValidUsername();
        user.firstName = Reader.getStringAnswer("Vorname:");
        user.lastName = Reader.getStringAnswer("Nachname:");
        user.email = Reader.getValidEmailAnswer("E-mail:");
        user.password = getPasswordHashed("Passwort:");
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
            case 2319:
                Print.printEgg();
                if (login()) {
                    int addedMoney = 1000000;
                    loggedInUser.accountBalance += addedMoney;
                    Print.printMoneyGift(addedMoney);
                    SQL.SetInDB.updateUser(loggedInUser);
                }
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
                //Print.printUserPortfolio(loggedInUser);
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
            case 5:
                return;
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
                    investmentNames = new String[shares.size()];
                    IntStream.range(0, shares.size()).forEach(i -> investmentNames[i] = shares.get(i).name);
                    break;
                case 2:
                    investmentNames = new String[resources.size()];
                    IntStream.range(0, resources.size()).forEach(i -> investmentNames[i] = resources.get(i).name);
                    break;
                case 3:
                    investmentNames = new String[cryptos.size()];
                    IntStream.range(0, cryptos.size()).forEach(i -> investmentNames[i] = cryptos.get(i).name);
                    break;
                case 4:
                    investmentNames = new String[etfs.size()];
                    IntStream.range(0, etfs.size()).forEach(i -> investmentNames[i] = etfs.get(i).name);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Diese Art von Investitionsmöglichkeit existiert nicht!");
                    investmentNames = null;
                    break;
            }
            if (investmentNames != null) {
                Print.printSelectedInvestmentList(investmentType, investmentNames);
            }
            TimeUnit.MILLISECONDS.sleep(2000);
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
        return (int) (amountOfMoney /= currentShare.pricePerShare);
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
        SQL.SetInDB.deleteUser(loggedInUser.userID);
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
        SQL.SetInDB.updateUser(loggedInUser);
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
        String newPassword = getPasswordHashed("Geben sie ein neues Passwort ein:");
        String verifyPassword = getPasswordHashed("Wiederholen sie ihr neues Passwort:");
        String oldPassword = getPasswordHashed("Geben sie ihr altes Passwort ein:");
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
        SQL.SetInDB.updateUser(loggedInUser);
    }

    private void setNewUsername() throws IOException {
        String newUsername = Reader.getStringAnswer("Geben sie einen neuen Username ein:");
        String verifyUsername = Reader.getStringAnswer("Wiederholen sie ihren Username:");
        String password = getPasswordHashed("Passwort:");
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
        SQL.SetInDB.updateUser(loggedInUser);
    }

    @NotNull
    private static String getPasswordHashed(String request) throws IOException {
        try {
            return Hash.getPasswordHashed(Reader.getStringAnswer(request));
        } catch (NoSuchAlgorithmException e) {
            Print.printError("Beim Hashen ist ein Fehler passiert!");
        }
        return "";
    }

    private boolean doStringsEqual(String input, String input1) {
        return input.equalsIgnoreCase(input1);
    }

    private void logOut() {
        loggedInUser = null;
    }

    private static void payOutMoney() {
        double amount = Reader.getIntAnswer("Wie viel Geld möchten sie auszahlen?");
        if (hasEnoughMoney(amount)) {
            loggedInUser.accountBalance -= amount;
            System.out.println("Geld erfolgreich ausgezahlt!");
        } else {
            Print.printError("Du besitzt nicht so viel Geld!");
        }
        SQL.SetInDB.updateUser(loggedInUser);
    }

    private static boolean hasEnoughMoney(double amount) {
        return amount < loggedInUser.accountBalance;
    }

    private boolean login() throws IOException {
        String emailOrUsername = Reader.getStringAnswer("E-Mail / Benutzername:");
        String password = getPasswordHashed("Passwort:");
        for (Map.Entry<String, User> entry : userList.entrySet()) {
            User user = entry.getValue();
            if ((emailOrUsername.equalsIgnoreCase(user.email) || emailOrUsername.equalsIgnoreCase(user.username)) && password.equalsIgnoreCase(user.password)) {
                Print.loginSucceed(user);
                loggedInUser = user;
                return true;
            }
        }
        Print.printError("Keinen Benutzer mit diesem Benutzernamen oder Passwort gefunden...");
        return false;
    }

    private void register() throws IOException {
        User newUser = new User();
        getUserInformation(newUser);
        userList.put(newUser.userID, newUser);
        SQL.SetInDB.addUserToDB(newUser);
        System.out.println("Erfolgreich registriert!");
    }

    static boolean isUsernameFree(String usernameInput) {
        for (Map.Entry<String, User> entry : userList.entrySet()) {
            User user = entry.getValue();
            if (usernameInput.equals(user.username)) {
                return false;
            }
        }
        return true;
    }

    public static long getMillis() {
        return System.currentTimeMillis();
    }
}
