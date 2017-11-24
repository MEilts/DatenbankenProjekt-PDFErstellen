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
public class Position {

    public Integer nummer, menge;
    public String bezeichnung;
    public Double einzelpreis, gesamtpreis;

    public Position() {
        this.nummer = null;
        this.menge = null;
        this.bezeichnung = null;
        this.einzelpreis = null;
        this.gesamtpreis = null;
    }

    public static List<Position> getPositionen(Connection con, int invoiceid) {
        ArrayList<Position> positionen = new ArrayList<>();
        try (ResultSet rs = con.createStatement().executeQuery("SELECT product.id, product.name, item.quantity, item.cost, item.cost*item.quantity as gesamt FROM item, product\n"
                + "where item.productid = product.id and item.invoiceid = " + invoiceid
                + "order by product.id")) {
            Position position;
            while (rs.next()) {
                position = new Position();
                position.nummer = rs.getInt("id");
                position.bezeichnung = rs.getString("name");
                position.menge = rs.getInt("quantity");
                position.einzelpreis = rs.getDouble("cost");
                position.gesamtpreis = rs.getDouble("gesamt");
                positionen.add(position);
            }
        } catch (Exception e) {
        }
        return positionen;
    }

    @Override
    public String toString() {
        return String.format("%15s %22s %9s %13s € %13s €", nummer, bezeichnung, menge, einzelpreis, gesamtpreis);
    }
}
