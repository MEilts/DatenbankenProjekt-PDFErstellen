/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rechnung.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author info
 */
public class Customer {
    
    private Integer id;
    private String firstname;
    private String lastname;
    private String street;
    private String city;

    public Customer() {
        this(null, null, null, null, null);
    }

    public Customer(Integer id, String firstname, String lastname, String street, String city) {            //Definition für einen Customer mit benutzerdaten
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.street = street;
        this.city = city;
    }
    
    public static Customer getCustomer(int rechnungsnummer){
        return null;
    }
        
    /**
     *
     * @param con
     * @param id
     * @return
     */
    public static Customer getCustomer(Connection con, Integer id) {
        Customer c = new Customer();                                            //Customer objekt mit der ID ... wird hier erstellt
        String sql = "SELECT * FROM Customer WHERE id = " + id;
        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            if (rs.next()) {
                c.id = rs.getInt("Id");
                c.firstname = rs.getString("firstname");
                c.lastname = rs.getString("lastname");
                c.street = rs.getString("lastname");
                c.city = rs.getString("city");
            }
        } catch (SQLException ex) {
            System.err.println("Error in getCustomer: " + ex);
        }
        return c;
    }

    /**
     * Get next Product from Resultset
     *
     * @param rs Resultset with Products
     * @return next Product in Resultset or null
     */
    public static Customer getCustomer(ResultSet rs) {
        Customer C = null;
        try {
            if (rs.next()) {
                C = new Customer();
                C.id = rs.getInt("Id");
                C.firstname = rs.getString("Name");
                C.lastname = rs.getString("Price");
                C.street = rs.getString("Street");
                C.city = rs.getString("city");
            }
        } catch (SQLException ex) {
            System.err.println("Error in getProduct: " + ex);
        }
        return C;
    }
    
         public static List<Customer> getCustomers(Connection con) {            //Erstellt eine Liste aller Customer
         List<Customer> list = new ArrayList<>();

        String sql = "SELECT * FROM Customer";                                  //Abfrage aller Customer

        try (Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(sql);

                                                                                // Alle gefundenen Produkte in die Liste eintragen
            Customer c = getCustomer(rs);
            while (c != null) {
                list.add(c);
                c = getCustomer(rs);
            }

        } catch (SQLException e) {
            System.err.println("Fehler bei der Abfrage: " + sql);               
        }

        return list;
    }      
    
}
