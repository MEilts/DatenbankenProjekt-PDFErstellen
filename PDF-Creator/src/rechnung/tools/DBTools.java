/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rechnung.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author info
 */
public class DBTools {
    
    static private final String TREIBER = "org.hsqldb.jdbcDriver";
    static private String uri = "jdbc:hsqldb:hsql://localhost/;shutdown=true", usr = "SA", psw = "";
    static Connection con;

    public static void setUri(String uri) {
        DBTools.uri = uri;
    }

    public static void setUsr(String usr) {
        DBTools.usr = usr;
    }

    public static void setPsw(String psw) {
        DBTools.psw = psw;
    }
    
    public static Connection getCon() {
        openDB();
        return con;
    }

    private static boolean openDB() {
        try {
            if (con != null && con.isValid(1)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class.forName(TREIBER);
            con = DriverManager.getConnection(uri + ";shutdown = true", usr, psw);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            con = null;
            return false;
        }
        return true;
    }
    
    public static boolean closeDB(){
        try {
            if(con != null && !con.isClosed()){
                con.close();
                con = null;
                return true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
            return false;
    }
}
