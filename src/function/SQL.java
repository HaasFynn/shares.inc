package function;

import archive.Transaction;
import game.User;
import investment_types.Portfolio;

import java.sql.*;

public class SQL {

    public static Connection connectToSQL(String database) {
        String url = "jdbc:mysql://localhost:3306/" + database; //jdbc:mysql://localhost:3306/phpmyadmin
        String user = "root";
        String pass = "";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Verbindung erfolgreich hergestellt");
        } catch (SQLException e) {
            Print.customStackPrint(e);
        }
        return con;
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

    public static void addUserToDB(User newUser) {
        createDataset();
        setDataInDataset(newUser);
    }

    private static void createDataset() {
        Connection connection = connectToSQL("shares");
        //code
        try (Statement stm = connection.createStatement()) {
            String insert = "INSERT INTO userdb(userID, username, firstname, lastname, email, password, accountbalance, usertransactionlist, userportfolio) VALUES (userID, username, firstname, lastname, email, password, accountbalance, usertransactionlist, userportfolio)"; //newUser.userID, newUser.username, newUser.firstName, newUser.lastName, newUser.email, newUser.password, newUser.accountBalance, newUser.userTransactionsList, newUser.userPortfolio
            stm.execute(insert);
        } catch (SQLException e) {
            Print.printError("Einfügen hat nicht geklappt!");
            Print.customStackPrint(e);
        }
        try {
            closeConnection(connection);
        } catch (Exception e) {
            Print.printError("Connection Failed!");
        }
    }

    private static void setDataInDataset(User user) {
        Connection connection = connectToSQL("shares");
        try (Statement stm = connection.createStatement()) {
            String update = "UPDATE userdb SET userID=" + user.userID + ", username=" + user.username + ", firstname=" + user.firstName + ", lastname=" + user.lastName + ", email=" + user.email + ", password=" + user.password + ", accountbalance=" + user.accountBalance + ", usertransactionlist=" + "currentUserTransactionlist" + ", userportfolio=" + "currentUserPortfolio" + " WHERE id=" + getLastDataset();
            //PreparedStatement preparedStatement = con.prepareStatement(update);
            //preparedStatement.setInt(1, getLastDataPK());
            //ResultSet rs = preparedStatement.executeQuery();
            stm.execute(update);
        } catch (SQLException e) {
            Print.printError("Beim Bearbeiten des Datensatzes ist ein Fehler passiert!");
            Print.customStackPrint(e);
        }
        try {
            closeConnection(connection);
        } catch (Exception e) {
            Print.printError("Connection Failed!");
        }
    }

    static int getLastDataset() {
        Connection connection = connectToSQL("shares");
        try (Statement stm = connection.createStatement();) {
            String quest = "SELECT * FROM userdb ORDER BY id DESC LIMIT 1;";
            ResultSet rs = stm.executeQuery(quest);
            return rs.getRow();
        } catch (SQLException e) {
            Print.printError("Beim Auslesen des Primarykeys ist ein Fehler unterlaufen!");
            Print.customStackPrint(e);
        }
        try {
            closeConnection(connection);
        } catch (Exception e) {
            Print.printError("Connection Failed!");
        }
        return 0;
    }

    public static void deleteUser(String userID) {
        Connection connection = connectToSQL("shares");
        try {
            String delete = "DELETE FROM userdb WHERE userID=" + userID;
            Statement stm = connection.createStatement();
            stm.execute(delete);
        } catch (SQLException e) {
            Print.printError("Beim Löschen eines Datensatzes ist ein Fehler unterlaufen!");
            Print.customStackPrint(e);
        }
        try {
            closeConnection(connection);
        } catch (Exception e) {
            Print.printError("Connection Failed!");
        }
    }

    public static void updateUser(User user) {
        Connection connection = connectToSQL("shares");
        try {
            String update = "UPDATE userdb SET userID=" + user.userID + ", username=" + user.username + ", firstname=" + user.firstName + ", lastname=" + user.lastName + ", email=" + user.email + ", password=" + user.password + ", accountbalance=" + user.accountBalance + ", usertransactionlist=" + "currentUserTransactionlist" + ", userportfolio=" + "currentUserPortfolio" + " WHERE userID=" + user.userID;
            System.out.println(update); // for testing
            Statement stm = connection.createStatement();
            stm.execute(update);
        } catch (SQLException e) {
            Print.printError("Beim Bearbeiten des Datensatzes ist ein Fehler passiert!");
            Print.customStackPrint(e);
        }
        try {
            closeConnection(connection);
        } catch (Exception e) {
            Print.printError("Connection Failed!");
        }
    }

    public static User getUserFromDB(int numberOfDatasets) {
        User user = new User();
        Connection connection = connectToSQL("shares");
        try (Statement stm = connection.createStatement()) {
            String select = "SELECT * FROM userdb WHERE id=" + numberOfDatasets + ";";
            ResultSet rs = stm.executeQuery(select);
            user.userID = rs.getString(1);
            user.username = rs.getString(2);
            user.firstName = rs.getString(3);
            user.lastName = rs.getString(4);
            user.email = rs.getString(5);
            user.password = rs.getString(6);
            user.accountBalance = rs.getInt(7);
            for (int i = 0; i < getLastDataset(); i++) {
                user.userTransactionList.add(getUserTransactionList(connection, i));
            }
            for (int i = 0; i < getLastDataset(); i++) {
                user.userPortfolio = getUserPortfolio(connection, i);
            }
        } catch (SQLException e) {
            Print.printError("Beim herauslesen der Userinformationen ist ein Fehler unterlaufen!");
            Print.customStackPrint(e);
        }
        return user;
    }

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

    private static Transaction getUserTransactionList(Connection connection, int index) {
        Transaction transaction = new Transaction();
        try (Statement stm = connection.createStatement()) {
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
}
