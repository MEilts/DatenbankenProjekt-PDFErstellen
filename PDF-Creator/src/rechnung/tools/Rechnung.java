/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rechnung.tools;

import java.sql.Connection;
import java.util.List;

/**
 *
 * @author Eilts,Marvin
 */
public class Rechnung {

    public Integer rechnungsnummer;
    public Invoice invoice;
    public Customer customer;
    public List<Position> positionen;

    public Rechnung() {
        this(null, null, null, null);
    }

    public Rechnung(Integer rechnungsnummer, Invoice invoice, Customer customer, List<Position> positionen) {
        this.rechnungsnummer = rechnungsnummer;
        this.invoice = invoice;
        this.customer = customer;
        this.positionen = positionen;
    }

    public static Rechnung getRechnung(Connection con, int invoiceid) {
        Rechnung rechnung = new Rechnung();
        rechnung.rechnungsnummer = invoiceid;
        rechnung.invoice = Invoice.getInvoice(con, invoiceid);
        rechnung.customer = Customer.getCustomer(con, rechnung.invoice.customerid);
        rechnung.positionen = Position.getPositionen(con, invoiceid);
        return rechnung;
    }

    @Override
    public String toString() {
        StringBuilder rechnung = new StringBuilder();
        rechnung.append(String.format("%80s", "I-Eilts EStore")).append(System.lineSeparator());
        rechnung.append(String.format("%80s", "Zweiundveirzig Str. 42")).append(System.lineSeparator());
        rechnung.append(String.format("%80s", "42224  Dortmund")).append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator());
        rechnung.append(System.lineSeparator());
        rechnung.append("  "+customer).append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator());
        rechnung.append(String.format("%68s: %-10s", "Rechnungsdatum", "22.05.1999")).append(System.lineSeparator());
        rechnung.append(String.format("%68s: %-10s", "Lieferdatum", "23.05.1999")).append(System.lineSeparator());
        rechnung.append(String.format("%68s: %-10s", "Kundennummer", customer.id)).append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator());
        rechnung.append("  Wir bedanken uns für die gute Zusammenarbeit und stellen Ihnen").append(System.lineSeparator());
        rechnung.append("  vereinbarungsgemäß folgende Lieferungen in Rechnung:").append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator());
        rechnung.append("  Rechnungsnummer: ").append(rechnungsnummer).append(System.lineSeparator()).append(System.lineSeparator());
        rechnung.append(String.format("%15s %22s %9s %15s %15s", "Artikelnummer", "Artikelbezeichnung", "Menge", "Einzelpreis", "Gesamtpreis"));
        rechnung.append(System.lineSeparator()).append("  ______________________________________________________________________________");
        rechnung.append(System.lineSeparator());
        positionen.stream().forEach(p -> rechnung.append(p).append(System.lineSeparator()));
        rechnung.append("  ______________________________________________________________________________").append(System.lineSeparator());
        rechnung.append(String.format("%-66s %11.2f €", "  Netto", invoice.total)).append(System.lineSeparator());
        rechnung.append(String.format("%-66s %11.2f €", "  zzgl. 19% MwSt.", invoice.total * 0.19)).append(System.lineSeparator());
        rechnung.append(String.format("%-66s %11.2f €", "  Rechnungsbetrag", invoice.total * 1.19)).append(System.lineSeparator());
        rechnung.append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator());
        rechnung.append("  Nach § 19 Abs. 1 UStG wird keine Umsatzsteuer berechnet.").append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator());
        rechnung.append("  Die Rechnung ist sofort fällig.").append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator());
        rechnung.append("  Bitte überweisen Sie den Rechnungsbetrag ohne Abzüge auf unser Bankkonto.").append(System.lineSeparator()).append(System.lineSeparator()).append(System.lineSeparator());
        rechnung.append("  Telefon: 01234/987654                       	        I-Eilts EStore Gmbh").append(System.lineSeparator());
        rechnung.append("  Fax:	   01234/987655                                 Sparkasse Dortmund").append(System.lineSeparator());
        rechnung.append("  Email:   mailto@i-eilts.com                           BLZ: 400422442").append(System.lineSeparator());
        rechnung.append("  Web:	   www.i-eilts.com				KTO: 424242442").append(System.lineSeparator());
        return rechnung.toString();
    }

}
