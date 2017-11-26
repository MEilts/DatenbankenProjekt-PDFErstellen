/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rechnung.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import rechnung.tools.DBTools;
import rechnung.tools.Product;

/**
 *
 * @author Eilts,Marvin
 */
public class Bestellung extends JPanel {

    private Product product;
    private double totalLonley, Total;
    private int anz;
    private JLabel id, name, einzel, gesamt;
    private JTextField input;

    public Bestellung(Product product) {
        super.setLayout(new GridLayout(1, 5, 2, 0));
        this.product = product;
        try (ResultSet rs = DBTools.getCon().createStatement().executeQuery("select cost from item where productid = " + product.id)) {
            rs.next();
            this.totalLonley = rs.getDouble(1);
        } catch (SQLException e) {
            System.err.println("Cost konnte nicht aus dem System entnommen werden");
            e.printStackTrace();
        }
        this.Total = 0;
        this.anz = 0;

        super.add(id = new JLabel(product.id + ""));
        super.add(name = new JLabel(product.name));
        super.add(einzel = new JLabel(toPreis(totalLonley)));
        super.add(input = new JTextField());
        super.add(gesamt = new JLabel(toPreis(Total)));

        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    anz = Integer.parseInt(input.getText());
                    input.setBackground(Color.white);
                } catch (Exception ex) {
                    anz = 0;
                    input.setBackground(Color.red);
                } finally {
                    Bestellung.this.getTotal(anz);
                }
            }
        });
    }
    
    private String toPreis(double p){
        return String.format("%9.2f €", p);
    }

    private void getTotal(int anz) {
        Total = totalLonley * anz;
        gesamt.setText(toPreis(Total));
    }

    public double getTotal() {
        return Total;
    }

    public double getTotalLonley() {
        return totalLonley;
    }

    public int getAnz() {
        return anz;
    }

    public JLabel getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

}
