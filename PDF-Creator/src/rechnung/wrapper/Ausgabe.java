/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rechnung.wrapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author info
 */
public class Ausgabe {

    public String ausgabe(ResultSet rs) throws SQLException {

        ResultSetMetaData mdat = rs.getMetaData();                           //Erstellt ein Objekt in der die Inf. gespeichert werden

        int sp = mdat.getColumnCount();

        StringBuilder z2 = new StringBuilder();                                                       //Erstellt den String z2
        String[] gstr = new String[mdat.getColumnCount()];                   //Erstellt den Array gstr & fügt die Spalten Zahl ein
        for (int i = 0; i < mdat.getColumnCount(); i++) {
            z2.append("|%");
            z2.append(mdat.getColumnDisplaySize(i));
            z2.append("s");                                                    //Erstellt einen String mit der Tab. formatierung
        }
        z2.append("%n");                                                    //ergänzt noch das ende (optisch)
        String form = z2.toString();
        StringBuilder out = new StringBuilder();
        while (rs.next()) {
            for (int i = 1; i <= mdat.getColumnCount(); i++) {
                gstr[i - 1] = rs.getString(i);
            }
            out.append(String.format(form, gstr));
        }
        return out.toString();
    }
}
