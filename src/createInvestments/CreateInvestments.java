package createInvestments;
import shares.function.*;

import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class CreateInvestments {

    static Random rand = new Random();

    public static void main(String[] args) {
        /*for (int i = 0; i < 10; i++) {
            createShare();
        }
        for (int i = 0; i < 10; i++) {
            createResource();
        }
        for (int i = 0; i < 10; i++) {
            createCrypto();
        }*/
        for (int i = 0; i < 3; i++) {
            createETF();
        }

    }
    public static void createShare() {
        Connection conn = SQL.Connect.getNewConnection("shares_investments");
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO share(name, shortl, company, stockreturn, exisitingshareamounts, dateofentry, pricepershare, companyvalue) VALUES(?,?,?,?,?,?,?,?)")) {
            ps.setString(1, Reader.getStringAnswer("Name"));
            ps.setString(2, Reader.getStringAnswer("shortl"));
            ps.setString(3, Reader.getStringAnswer("company"));
            ps.setDouble(4, rand.nextDouble(10000) + 5000);
            ps.setInt(5, rand.nextInt(1000000) + 100000);
            ps.setDate(6, new Date(System.currentTimeMillis()));
            ps.setDouble(7, Reader.getDoubleAnswer("PricePerShare?"));
            ps.setDouble(8, rand.nextDouble(1000000) + 100000);
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void createResource() {
        Connection conn = SQL.Connect.getNewConnection("shares_investments");
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resource(name, shortl, stockreturn, existingsharesamount, dateofentry, pricepershare) VALUES(?,?,?,?,?,?)")) {
            ps.setString(1, Reader.getStringAnswer("Name"));
            ps.setString(2, Reader.getStringAnswer("shortl"));
            ps.setDouble(3, Reader.getDoubleAnswer("stockreturn"));
            ps.setInt(4, rand.nextInt(1000000) + 100000);
            ps.setDate(5, new Date(System.currentTimeMillis()));
            ps.setDouble(6, Reader.getDoubleAnswer("PricePerShare?"));
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void createETF() {
        Connection conn = SQL.Connect.getNewConnection("shares_investments");
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO etf(name, stockreturn, pricepershare, dateOfEntry) VALUES(?,?,?,?)")) {
            ps.setString(1, Reader.getStringAnswer("Name"));
            ps.setDouble(2, Reader.getDoubleAnswer("stockreturn"));
            ps.setDouble(3, Reader.getDoubleAnswer("PricePerShare?"));
            ps.setDate(4, new Date(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void createCrypto() {
        Connection conn = SQL.Connect.getNewConnection("shares_investments");
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO crypto(name, pricepershare, stockreturn_in_percent, dateOfEntry) VALUES(?,?,?,?)")) {
            ps.setString(1, Reader.getStringAnswer("Name"));
            ps.setString(2, Reader.getStringAnswer("pricepershare:"));
            ps.setDouble(3, rand.nextDouble(10000) + 5000);
            ps.setDate(4, new Date(System.currentTimeMillis()));
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

}
