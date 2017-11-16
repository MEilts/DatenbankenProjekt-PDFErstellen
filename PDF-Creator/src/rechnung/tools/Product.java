package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for Product
 * @version 2.0
 * @author rla
 */
public class Product {

    public Integer id;
    public String name;
    public double price;

    /**
     * Constructor for empty Products
     */
    public Product() {
        id = null;
        name = null;
        price = Double.NaN;
    }

    /**
     * Get Product object
     *
     * @param con Database connection
     * @param id primary Key
     * @return
     */
    public static Product getProduct(Connection con, Integer id) {
        Product p = new Product();
        String sql = "SELECT * FROM Product WHERE id = " + id;
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                p.id = rs.getInt("Id");
                p.name = rs.getString("Name");
                p.price = rs.getDouble("Price");
            }
        } catch (SQLException ex) {
            System.err.println("Error in getProduct: " + ex);
        }
        return p;
    }

    /**
     * Get next Product from Resultset
     *
     * @param rs Resultset with Products
     * @return next Product in Resultset or null
     */
    public static Product getProduct(ResultSet rs) {
        Product p = null;
        try {
            if (rs.next()) {
                p = new Product();
                p.id = rs.getInt("Id");
                p.name = rs.getString("Name");
                p.price = rs.getDouble("Price");
            }
        } catch (SQLException ex) {
            System.err.println("Error in getProduct: " + ex);
        }
        return p;
    }

    /**
     * Ermittelt den hoechsten Produktpreis.
     *
     * @param con Database connection
     * @return maximaler Preis
     */
    public static double getMaxPrice(Connection con) {
        double mp = Double.NaN;
        String sql = "SELECT MAX(price) FROM Product";
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                mp = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            System.err.println("Error in getMaxPrice " + ex);
        }
        return mp;
    }

    /**
     * Liefert die Liste aller Produkte im Bereich minPreis bis maxPreis.
     * @param con
     * @param minPreis einschliesslich
     * @param maxPreis ausschliesslich
     * @return 
     */
    public static List<Product> getProducts(Connection con, double minPreis, double maxPreis) {
        List<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM Product"
                + " WHERE price >= " + minPreis + " AND price < " + maxPreis +
                " ORDER BY price ASC";

        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);

            // Alle gefundenen Produkte in die Liste eintragen
            Product p = getProduct(rs);
            while (p != null) {
                list.add(p);
                p = getProduct(rs);
            }

        } catch (SQLException e) {
            System.err.println("Fehler bei der Abfrage: " + sql);
        }

        return list;
    }

    /**
     * Creates a String representation of a product object. "Name \t Price"
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("%4d  %-20s  %5.2f EUR", this.id, this.name, this.price);
    }

}
