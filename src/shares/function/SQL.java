package shares.function;

import shares.archive.Price;
import shares.archive.Transaction;
import shares.game.User;
import shares.investment_types.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import static shares.function.SQL.Connect.getHighestIDEntry;
import static shares.function.SQL.Connect.getNewConnection;

public class SQL {

    public static class Connect {
        public static Connection getNewConnection(String database) {
            String url = "jdbc:mysql://localhost:3306/" + database; //jdbc:mysql://localhost:3306/phpmyadmin
            String user = "root";
            String pass = "";
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, pass);
            } catch (SQLException e) {
                Print.customStackPrint(e);
            }
            return conn;
        }

        private static void closeConnection(Connection connection) {
            try {
                connection.close();
                System.out.println("Verbindung erfolgreich hergestellt");
            } catch (SQLException e) {
                System.out.println("Beim schliessen der Verbindung ist ein Fehler geschehen!");
                Print.customStackPrint(e);
            }
        }

        public static int getHighestIDEntry(Connection conn, String table, String columnName) {
            int highestEntry = 0;
            try (Statement stm = conn.createStatement()) {
                String select = "SELECT " + columnName + " FROM " + table + " WHERE  " + columnName + "=(SELECT MAX(" + columnName + ") FROM " + table + ")";
                ResultSet rs = stm.executeQuery(select);
                rs.next();
                highestEntry = rs.getInt(1);
            } catch (SQLException e) {
                Print.printError("Beim herauslesen des höchsten Integer eintrags ist ein Fehler unterlaufen!");
                Print.customStackPrint(e);
            }
            return highestEntry;
        }
    }

    public static class SetInDB {
        public static void addUserToDB(User newUser) {
            setDataInDataset(newUser);
        }

        private static void setDataInDataset(User user) {
            Connection conn = Connect.getNewConnection("shares_main");
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO userdb(user_id, username, firstname, lastname, email, password, accountbalance, userShares_id, userResource_id, userCrypto_id, userETF_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
                ps.setString(1, user.userID);
                ps.setString(2, user.username);
                ps.setString(3, user.firstName);
                ps.setString(4, user.lastName);
                ps.setString(5, user.email);
                ps.setString(6, user.password);
                ps.setDouble(7, user.accountBalance);
                ps.setDouble(8, getHighestIDEntry(conn, "userdb", "userShares_id") + 1);
                ps.setDouble(9, getHighestIDEntry(conn, "userdb", "userResource_id") + 1);
                ps.setDouble(10, getHighestIDEntry(conn, "userdb", "userCrypto_id") + 1);
                ps.setDouble(11, getHighestIDEntry(conn, "userdb", "userETF_id") + 1);
                ps.executeUpdate();
            } catch (SQLException e) {
                Print.printError("Beim Bearbeiten des Datensatzes ist ein Fehler passiert!");
                Print.customStackPrint(e);
            } finally {
                Connect.closeConnection(conn);
            }
        }

        public static void deleteUser(String userID) {
            Connection conn = Connect.getNewConnection("shares_main");
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM userdb WHERE userID=?")) {
                ps.setString(1, userID);
                ps.executeQuery();
            } catch (SQLException e) {
                Print.printError("Beim Löschen eines Datensatzes ist ein Fehler unterlaufen!");
                Print.customStackPrint(e);
            } finally {
                Connect.closeConnection(conn);
            }
        }

        public static void updateUser(User user) {
            Connection conn = Connect.getNewConnection("shares_main");
            try (PreparedStatement ps = conn.prepareStatement("UPDATE userdb SET username=?, firstname=?, lastname=?, email=?, password=?, accountbalance=? WHERE user_id=?")) {
                ps.setString(1, user.username);
                ps.setString(2, user.firstName);
                ps.setString(3, user.lastName);
                ps.setString(4, user.email);
                ps.setString(5, user.password);
                ps.setDouble(6, user.accountBalance);
                ps.setString(7, user.userID);
                ps.executeUpdate();
            } catch (SQLException e) {
                Print.printError("Beim Updaten eines User-Eintrags ist ein Fehler unterlaufen!");
                e.printStackTrace();
            }
        }
    }

    public static class GetFromDB {

        private static Portfolio getUserPortfolio(Connection connection, int index) {
            Portfolio userportfolio = new Portfolio();
            try (Statement stm = connection.createStatement()) {
                String select = "SELECT * FROM transaction WHERE id=" + index;
                ResultSet rs = stm.executeQuery(select);
            } catch (SQLException e) {
                Print.printError("Beim auslesen der Transaktionsliste ist ein Fehler aufgetreten!");
                Print.customStackPrint(e);
            }
            return userportfolio;
        }

        public static User getUserFromDB(int currentIndex) {
            User user = new User();
            Connection conn = Connect.getNewConnection("shares_main");
            try (Statement stm = conn.createStatement()) {
                String select = "SELECT * FROM userdb WHERE id=" + currentIndex + ";";
                ResultSet rs = stm.executeQuery(select);
                if (rs.next()) {
                    user.userID = rs.getString(2);
                    user.username = rs.getString(3);
                    user.firstName = rs.getString(4);
                    user.lastName = rs.getString(5);
                    user.email = rs.getString(6);
                    user.password = rs.getString(7);
                    user.accountBalance = rs.getInt(8);
                    return user;
                }
            } catch (SQLException e) {
                Print.printError("Beim herauslesen der User-Informationen ist ein Fehler unterlaufen!");
                Print.customStackPrint(e);
            }
            return null;
        }

        private static Transaction getTransaction(Connection conn, int index) {
            Transaction transaction = new Transaction();
            try (Statement stm = conn.createStatement()) {
                String select = "SELECT * FROM transaction WHERE id=" + index;
                ResultSet rs = stm.executeQuery(select);
                transaction.transactionID = rs.getString(1);
                transaction.date = rs.getDate(2);
                transaction.senderID = rs.getString(3);
                transaction.receiverID = rs.getString(4);
                transaction.Investment = rs.getString(5);
                transaction.amount = rs.getInt(6);
                transaction.transferAmount = rs.getDouble(7);
            } catch (SQLException e) {
                Print.printError("Beim auslesen der Transaktionsliste ist ein Fehler aufgetreten!");
                Print.customStackPrint(e);
            }
            return transaction;
        }

        public static ArrayList<Share> getListOfSharesFromDB() {
            ArrayList<Share> shareList = new ArrayList<>();
            for (int i = 1; i <= getAmountOfRegisteredSets("shares_investments", "share"); i++) {
                shareList.add(getShare(i));
            }
            return shareList;
        }

        private static Share getShare(int i) {
            Share share = new Share();
            Connection conn = Connect.getNewConnection("shares_investments");
            try (Statement stm = conn.createStatement()) {
                String statement = "SELECT * FROM share WHERE id=" + i;
                ResultSet rs = stm.executeQuery(statement);
                rs.next();
                share.name = rs.getString(2);
                share.shortl = rs.getString(3);
                share.company = rs.getString(4);
                share.stockReturnInPercent = rs.getDouble(5);
                share.existingSharesAmount = rs.getInt(6);
                share.dateOfEntry = rs.getDate(7);
                share.pricePerShare = rs.getDouble(8);
                share.companyValue = rs.getDouble(9);
            } catch (SQLException e) {
                Print.printError("Beim auslesen einer Aktie ist ein Fehler unterlaufen!");
                Print.customStackPrint(e);
            }
            return share;
        }

        private static Price getPriceClassData(Connection conn, int index) {
            Price price = new Price(new Date(Operator.getMillis()), 0);
            try (Statement stm = conn.createStatement()) {
                String select = "SELECT * FROM transaction WHERE id=" + index;
                ResultSet rs = stm.executeQuery(select);
                price.currentPrice = rs.getDouble(1);
            } catch (SQLException e) {
                Print.printError("Beim auslesen der Transaktionsliste ist ein Fehler aufgetreten!");
                Print.customStackPrint(e);
            }
            return price;
        }

        public static int getAmountOfRegisteredSets(String database, String table) {
            Connection conn = Connect.getNewConnection(database);
            String request = "SELECT COUNT(*) AS totalEntries FROM " + table;
            try (Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery(request)) {
                rs.next();
                return rs.getInt("totalEntries");
            } catch (SQLException e) {
                Print.printError("Beim Zählen Anzahl der Einträge ist ein Fehler unterlaufen!");
                Print.customStackPrint(e);
            }
            return 0;
        }

        public static ArrayList<Resource> getListOfResourcesFromDB() {
            ArrayList<Resource> resourceList = new ArrayList<>();
            for (int i = 1; i <= getAmountOfRegisteredSets("shares_investments", "resource"); i++) {
                resourceList.add(getResource(i));
            }
            return resourceList;
        }

        private static Resource getResource(int i) {
            Resource resource = new Resource();
            Connection conn = Connect.getNewConnection("shares_investments");
            try (Statement stm = conn.createStatement()) {
                String statement = "SELECT * FROM resource WHERE id=" + i;
                ResultSet rs = stm.executeQuery(statement);
                rs.next();
                resource.name = rs.getString(2);
                resource.shortl = rs.getString(3);
                resource.stockReturnInPercent = rs.getDouble(4);
                resource.existingSharesAmount = rs.getInt(5);
                resource.dateOfEntry = rs.getDate(6);
                resource.pricePerShare = rs.getDouble(7);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resource;
        }

        public static ArrayList<Crypto> getListOfCryptoFromDB() {
            ArrayList<Crypto> cryptos = new ArrayList<>();
            for (int i = 1; i <= getAmountOfRegisteredSets("shares_investments", "crypto"); i++) {
                cryptos.add(getCrypto(i));
            }
            return cryptos;
        }

        private static Crypto getCrypto(int i) {
            Crypto crypto = new Crypto();
            Connection conn = Connect.getNewConnection("shares_investments");
            try (Statement stm = conn.createStatement()) {
                String statement = "SELECT * FROM crypto WHERE id=" + i;
                ResultSet rs = stm.executeQuery(statement);
                rs.next();
                crypto.name = rs.getString(2);
                crypto.shortl = rs.getString(3);
                crypto.pricePerShare = rs.getDouble(4);
                crypto.stockReturnInPercent = rs.getDouble(5);
                crypto.existingSharesAmount = rs.getInt(6);
                crypto.dateOfEntry = rs.getDate(7);
            } catch (SQLException e) {
                Print.printError("Beim Holen der Cryptos ist ein Fehler passiert!");
            }
            return crypto;
        }

        public static HashMap<Date, Double> getPricesOverTime() {
            return null;
        }

        public static ArrayList<ETF> getListOfETFsFromDB(ArrayList<ETF> etfs) {
            for (int i = 1; i <= getAmountOfRegisteredSets("shares_investments", "etf"); i++) {
                etfs.add(getETF(i));
            }
            return etfs;
        }

        private static ETF getETF(int i) {
            ETF etf = new ETF();
            Connection conn = Connect.getNewConnection("shares_investments");
            try (Statement stm = conn.createStatement()) {
                String statement = "SELECT * FROM etf WHERE id=" + i;
                ResultSet rs = stm.executeQuery(statement);
                rs.next();
                etf.name = rs.getString(2);
                etf.stockReturnInPercent = rs.getDouble(3);
                etf.pricePerShare = rs.getDouble(4);
                etf.dateOfEntry = rs.getDate(5);
            } catch (SQLException e) {
                Print.printError("Beim Holen der ETF's ist ein Fehler passiert!");
            }
            return etf;
        }

        public static int getHighestRegisteredID() {
            Connection conn = Connect.getNewConnection("shares_main");
            try (Statement stm = conn.createStatement()) {
                String statement = "SELECT MAX(id) FROM userdb";
                ResultSet rs = stm.executeQuery(statement);
                rs.next();
                return rs.getInt(1);
            } catch (SQLException e) {
                Print.printError("Beim herauslesen der höchsten ID ist ein Fehler unterlaufen!");
                e.printStackTrace();
            }
            return 0;
        }
    }
}