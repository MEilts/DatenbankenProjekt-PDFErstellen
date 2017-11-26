/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rechnung.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import rechnung.tools.DBTools;
import rechnung.tools.Product;

/**
 *
 * @author Eilts,Marvin
 */
public class OutputPanel extends JPanel {

    List<Bestellung> bills = new ArrayList<>();

    public OutputPanel() {
        super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Bestellung b;
        for (Product p : Product.getProducts(DBTools.getCon())) {
            super.add(b = new Bestellung(p));
            bills.add(b);
        }
    }

    public List<Bestellung> getBills() {
        return bills;
    }

}
