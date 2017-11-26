/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rechnung.topdf;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.*;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.BaseFont;
import rechnung.tools.Bill;
import rechnung.tools.Position;

public class CreatePDF {

    public static Document document = new Document(PageSize.A4);
    public static PdfWriter writer;

    public static void pdf(Bill bill) {
        Document document = new Document();

        try {
            writer = PdfWriter.getInstance(document, new FileOutputStream(System.getProperty("user.home") + "/Rechnung" + bill.billNumber + ".pdf"));

            document.open();
            Rectangle border = new Rectangle(0f, 0f);
            //border.setBorderWidthLeft(6f);
            //border.setBorderWidthRight(5f);

//            Image image = Image.getInstance("bilder/nouvellecom.png");
//            image.scaleAbsolute(244.6f, 42.3f);
//            image.setAbsolutePosition(20f, 770f);
//            document.add(image);
//
//            image = Image.getInstance("bilder/qsc.png");
//            image.scaleAbsolute(120f, 50.8f);
//            image.setAbsolutePosition(425f, 760f);
//            document.add(image);
//
//            image = Image.getInstance("bilder/roter_strich.png");
//            image.setAbsolutePosition(0f, 730f);
//            document.add(image);
            String eintext;
            //Adresse Absender
            eintext = "I-Eilts EStore  | Zweiundveirzig Str. 42 | 42224  Dortmund";
            text(eintext, 30, 680, 6, 0);

            // Daten Absender
            eintext = "I-Eilts EStore";
            text(eintext, 400, 680, 8, 0);
            eintext = "Zweiundveirzig Str. 42";
            text(eintext, 400, 670, 8, 0);
            eintext = "42224  Dortmund";
            text(eintext, 400, 660, 8, 0);
            eintext = "Onlineshop";
            text(eintext, 400, 650, 8, 0);

            eintext = "Tel 0231/ 42224";
            text(eintext, 365, 630, 8, 0);

            eintext = "E-Mail info@I-Eilts-EStore.de";
            text(eintext, 365, 610, 8, 0);

            eintext = "Web www.I-Eilts-EStore.de";
            text(eintext, 365, 600, 8, 0);

            eintext = "Datum";
            text(eintext, 365, 590, 8, 0);
            eintext = "17.12.2012";
            text(eintext, 400, 590, 8, 0);

            //Anschrift Firma
            eintext = bill.customer.firstname + " " + bill.customer.lastname;
            text(eintext, 30, 620, 10, 1);

            eintext = bill.customer.street;
            text(eintext, 30, 610, 10, 1);

            eintext = bill.customer.city;
            text(eintext, 30, 600, 10, 1);

            //Betreff
            eintext = "Rechnung zur Bestellung " + bill.billNumber;
            text(eintext, 30, 500, 10, 1);

            //Ansprache
            eintext = "Sehr geehrte[r] Frau/Herr " + bill.customer.lastname;
            text(eintext, 30, 470, 10, 1);

            //Einleitungstext
            eintext = "vielen Dank für ihre Bestellung. Nachfolgend finden sie eine auflistung ihrer Positionen";
            text(eintext, 30, 450, 10, 0);

            eintext = String.format("%15s %22s %9s %15s %15s", "Artikelnummer", "Artikelbezeichnung", "Menge", "Einzelpreis", "Gesamtpreis");
            text(eintext, 30, 430, 10, 0);
            eintext = "______________________________________________________________________________";
            text(eintext, 30, 420, 10, 0);
            int zahl = 400;
            for (Position p : bill.Postions) {
                text(p.Number + "", 30, zahl, 10, 0);
                text(p.Description + "", 120, zahl, 10, 0);
                text(p.Count + "", 250, zahl, 10, 0);
                text(p.TotalLonley + "", 290, zahl, 10, 0);
                text(p.Total + "", 340, zahl, 10, 0);
                zahl -= 10;
            }
            eintext = "______________________________________________________________________________";
            text(eintext, 30, zahl-10, 10, 0);
            eintext = String.format("%-66s %11.2f €", "  Netto      ", bill.invoice.total);
            text(eintext, 30, zahl-20, 10, 0);
            eintext = String.format("%-66s %11.2f €", "  zzgl. 19% MwSt. ", bill.invoice.total * 0.19);
            text(eintext, 30, zahl-30, 10, 0);
            eintext = String.format("%-66s %11.2f €", "  Rechnungsbetrag", bill.invoice.total * 1.19);
            text(eintext, 30, zahl-40, 10, 0);

            eintext = "Nach § 19 Abs. 1 UStG wird keine Umsatzsteuer berechnet.";
            text(eintext, 30, zahl-60, 10, 0);
            eintext = "Die Rechnung ist sofort fällig.";
            text(eintext, 30, zahl-70, 10, 0);
            eintext = "Bitte überweisen Sie den Rechnungsbetrag ohne Abzüge auf unser Bankkonto.";
            text(eintext, 30, zahl-80, 10, 0);
            
            eintext = "Telefon: 01234/987654";
            text(eintext, 30, zahl-200, 6, 0);
            eintext ="I-Eilts EStore Gmbh";
            text(eintext, 350, zahl-200, 6, 0);
            
            eintext = "Fax:	   01234/987655";   
            text(eintext, 30, zahl-210, 6, 0);
            eintext ="Sparkasse Dortmund";
            text(eintext, 350, zahl-210, 6, 0);
            
            eintext = "Email:   mailto@i-eilts.com"; 
            text(eintext, 30, zahl-220, 6, 0);
            eintext ="BLZ: 400422442";
            text(eintext, 350, zahl-220, 6, 0);
            
            eintext = "Web:	   www.i-eilts.com";
            text(eintext, 30, zahl-230, 6, 0);
            eintext ="KTO: 424242442";
            text(eintext, 350, zahl-230, 6, 0);
            
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void text(String text, int x, int y, int sg, int bold) {
        Document document = new Document();
        try {
            PdfContentByte cb = writer.getDirectContent();

            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            BaseFont bfbold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.saveState();
            cb.beginText();
            cb.moveText(x, y);
            cb.setFontAndSize(bf, sg);

            cb.setFontAndSize(bfbold, sg);

            cb.newlineShowText(text);
            cb.endText();
            cb.restoreState();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
