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
public class Product {
    
    public Integer id;
    public String name;
    public Double price;

    public Product() {
        this(null, null, null);
    }

    public Product(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    public static Product getProduct(Connection con, int id){
        Product out = new Product();
        try (ResultSet rs = con.createStatement().executeQuery("select * from product where id = " + id)) {
            if (rs.next()) {
                out.id = rs.getInt("id");
                out.name = rs.getString("name");
                out.price = rs.getDouble("price");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }
    
    public static List<Product> getProducts(Connection con) {
        Product out;
        List<Product> outlist = new ArrayList<>();
        try (ResultSet rs = con.createStatement().executeQuery("select * from product ")) {
            while (rs.next()) {
                out = new Product();
                out.id = rs.getInt("id");
                out.name = rs.getString("name");
                out.price = rs.getDouble("price");
                outlist.add(out);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outlist;
    }
    
    @Override
    public String toString() {
        return id+" "+name+" "+price;
    }
    
}
