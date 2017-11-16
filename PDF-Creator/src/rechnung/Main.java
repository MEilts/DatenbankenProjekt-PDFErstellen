/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rechnung;

import rechnung.tools.DBTools;

/**
 *
 * @author info
 */
public class Main {
    
    public static void main(String[] args) {
        DBTools dbt = new DBTools();
        dbt.getCon();
//        System.out.print("SELECT customer.firstname, product.name FROM item, invoice, customer, product\n" +
//"WHERE invoice.ID = 3\n" +
//"and invoice.customerid = customer.id\n" +
//"and invoice.id = item.invoiceid\n" +
//"and product.id = item.productid");
        dbt.closeDB();
    }
    
}
