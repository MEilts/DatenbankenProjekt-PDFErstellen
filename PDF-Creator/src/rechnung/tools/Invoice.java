/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rechnung.tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author info
 */
public class Invoice {
    public Integer id;
    public Integer customerid;
    public Double total;

    public Invoice() {
        this(null, null, null);
    }

    public Invoice(Integer id, Integer customerid, Double total) {
        this.id = id;
        this.customerid = customerid;
        this.total = total;
    }
    
    public static Invoice getInvoice(Connection con, int id){
        Invoice inv = new Invoice();
        try (ResultSet rs = con.createStatement().executeQuery("select * from invoice where id = " + id)) {
            if (rs.next()) {
                inv.id = rs.getInt("id");
                inv.customerid = rs.getInt("customerid");
                inv.total = rs.getDouble("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inv;
    }
    
    public static List<Invoice> getInvoice(Connection con) {
        Invoice inv;
        List<Invoice> outlist = new ArrayList<>();
        try (ResultSet rs = con.createStatement().executeQuery("select * from invoice ")) {
            while (rs.next()) {
                inv = new Invoice();
                inv.id = rs.getInt("id");
                inv.customerid = rs.getInt("customerid");
                inv.total = rs.getDouble("total");
                outlist.add(inv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outlist;
    }
    
    @Override
    public String toString() {
        return id+" "+customerid+" "+total;
    }
    
}
