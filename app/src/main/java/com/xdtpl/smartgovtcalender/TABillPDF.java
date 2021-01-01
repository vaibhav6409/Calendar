package com.xdtpl.smartgovtcalender;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TABillPDF {
    private Context context;
    private File pdfFile1;
    private Document document, document2;
    private PdfWriter pdfwriter;
    private Paragraph paragraph;
    BaseFont marfont;
    java.util.List<Integer> fields = new ArrayList<>();
    // java.util.List<Double> Rate = new ArrayList<>();
    Double Rate;
    public Double TotalAmt = 0.0;

    // private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
    //private Font fSubTitle = new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD);
    private Font fText = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL);
    //private Font fHighText = new Font(Font.FontFamily.TIMES_ROMAN,20,Font.BOLD,BaseColor.RED);

    public static final String FONT = "assets/NotoSansDevanagari-Regular.ttf";
    BaseFont bf_russian = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    Font russian = new Font(bf_russian, 18);
    Font russian1 = new Font(bf_russian, 25, Font.BOLD);
    Font russian2 = new Font(bf_russian, 25, Font.NORMAL);

    public TABillPDF(Context context, AssetManager asset) throws IOException, DocumentException {
        this.context = context;
        //BaseFont marfont = BaseFont.createFont("assets/Kruti_Dev_010.ttf", "UTF-8",BaseFont.EMBEDDED);

        //marfont = Typeface.createFromAsset(asset,"Kruti_Dev_010.ttf");
        //fTitle.setFamily(marfont);

        //fTitle = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
    }

    public void openDocument(String filename) {
        createFile(filename);
        try {
            document = new Document(PageSize.A2);
            pdfwriter = pdfwriter.getInstance(document, new FileOutputStream(pdfFile1));
            document.open();
            MyFooter footerEvent = new MyFooter();
            pdfwriter.setPageEvent(footerEvent);
            document.newPage();

        } catch (Exception e) {
            Log.e("Open Document", e.toString());
        }
    }

    private void createFile(String filename) {
        File folder = new File(Environment.getExternalStorageDirectory() + "/Download/Calendar/");
        if (!folder.exists())
            folder.mkdir();
        try {
            File file = new File(Environment.getRootDirectory().toString() + File.separator + filename + ".pdf");
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pdfFile1 = new File(folder, filename + ".pdf");
    }

    public void closeDocument() {
        document.close();
    }

    public void addMetaData(String title, String subject, String author) {
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
    }

    private Image GetItextImage(String text, boolean isSplit, int font) {
        Bitmap bm = textAsBitmap(text, font, Color.BLACK, isSplit);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image img = null;
        byte[] byteArray = stream.toByteArray();
        try {
            img = Image.getInstance(byteArray);
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private Image GetItextImage1(String text, int wdth, int rowcount , int font) {
        Bitmap bm = textAsBitmap1(text, font, Color.BLACK, wdth, rowcount);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image img = null;
        byte[] byteArray = stream.toByteArray();
        try {
            img = Image.getInstance(byteArray);
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    //1rd page 1st table start
   /* public void addName(String shorttext1){
        try {
            paragraph = new Paragraph();
            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100f);
            int indexC = 0;
            PdfPCell cell = new PdfPCell(new Paragraph(shorttext1,russian));
            PdfPCell cell1 = new PdfPCell(new Paragraph(shorttext1,russian));

            cell.setFixedHeight(40);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            table.addCell(cell1);

            document.add(table);
        }catch (Exception e){
            Log.e("createTable",e.toString());
        }
    }*/

    public void createfirstTitles(String[] shorttext) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(shorttext.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < shorttext.length) {
                pdfPCell = new PdfPCell(GetItextImage(shorttext[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.newPage();
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createfirstpageheder2(String[] firstpageheder2) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(firstpageheder2.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < firstpageheder2.length) {
                pdfPCell = new PdfPCell(GetItextImage(firstpageheder2[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createfirstheader3(String subtitle5, String month) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);

            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100f);
            // Create cells
            PdfPCell cell1 = new PdfPCell(GetItextImage("शा.नि.वित्त विभाग क्र.टीआरडब्ल्यु-2481/प्रक्र-719/कोष 4 दि. 27 मे 1987", false, 18));
            PdfPCell cell2 = new PdfPCell(GetItextImage("म. को. नि. नमुना १८", false, 18));
            PdfPCell cell13 = new PdfPCell(GetItextImage("(नियम 249 आणि 273)", false, 18));
            PdfPCell cell14 = new PdfPCell(GetItextImage("प्रवास भत्ता देयक", false, 40));

            // Add cells in table
            cell1.setFixedHeight(40);
            cell1.setPadding(5);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);

            cell2.setFixedHeight(40);
            cell2.setPadding(5);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell2);

            cell13.setFixedHeight(40);
            cell13.setPadding(5);
            cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell13);

            cell14.setFixedHeight(40);
            cell14.setPadding(5);
            cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell14);

            PdfPTable table2 = new PdfPTable(2);
            table2.setWidthPercentage(100f);
            PdfPCell cell = new PdfPCell(GetItextImage("कार्यालयाचे नाव : " + subtitle5, false, 18));
            PdfPCell cell3 = new PdfPCell(GetItextImage("माहे : " + month, false, 18));
            cell.setFixedHeight(60);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table2.addCell(cell);

            cell3.setFixedHeight(60);
            cell3.setPadding(5);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            table2.addCell(cell3);

            PdfPTable table3 = new PdfPTable(5);
            table3.setWidthPercentage(100f);
            PdfPCell cell31 = new PdfPCell(GetItextImage("  कोषागारासाठी", false, 18));
            PdfPCell cell32 = new PdfPCell(new Paragraph(""));
            PdfPCell cell33 = new PdfPCell(new Paragraph(""));
            PdfPCell cell34 = new PdfPCell(new Paragraph(""));
            PdfPCell cell35 = new PdfPCell(new Paragraph(""));

            cell.setFixedHeight(40);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table3.addCell(cell31);

            cell32.setFixedHeight(40);
            cell32.setPadding(5);
            cell32.setHorizontalAlignment(Element.ALIGN_LEFT);
            table3.addCell(cell32);

            cell33.setFixedHeight(40);
            cell33.setPadding(5);
            cell33.setHorizontalAlignment(Element.ALIGN_LEFT);
            table3.addCell(cell33);

            cell34.setFixedHeight(40);
            cell34.setPadding(5);
            cell34.setHorizontalAlignment(Element.ALIGN_LEFT);
            table3.addCell(cell34);

            cell35.setFixedHeight(40);
            cell35.setPadding(5);
            cell35.setHorizontalAlignment(Element.ALIGN_LEFT);
            table3.addCell(cell35);

            PdfPTable table4 = new PdfPTable(2);
            table4.setWidthPercentage(100f);
            float[] columnWidths = {0.141f, 0.565f}; // Second column will be
            // twice as first and third
            table4.setWidths(columnWidths);

            PdfPCell cell23 = new PdfPCell(GetItextImage("ओळखचिन्ह क्रमांक" +
                    " दिनांक :", true, 18));
            cell23.setRowspan(4);
            cell23.setPadding(50);
            cell23.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.addCell(cell23);

            PdfPCell cell41 = new PdfPCell(new Phrase("HEAD OF ACCOUNT", fText));
            cell41.setFixedHeight(40);
            cell41.setPadding(5);
            cell41.setHorizontalAlignment(Element.ALIGN_CENTER);
            table4.addCell(cell41);

            PdfPCell cell42 = new PdfPCell(new Phrase("Administrative Department  :", fText));
            cell42.setFixedHeight(40);
            cell42.setPadding(5);
            cell42.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.addCell(cell42);

            PdfPCell cell43 = new PdfPCell(new Phrase("Demand No                          :", fText));
            cell43.setFixedHeight(40);
            cell43.setPadding(5);
            cell43.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.addCell(cell43);

            PdfPCell cell44 = new PdfPCell(new Phrase("Major Head                          :", fText));
            cell44.setFixedHeight(40);
            cell44.setPadding(5);
            cell44.setHorizontalAlignment(Element.ALIGN_LEFT);
            table4.addCell(cell44);

            PdfPTable table5 = new PdfPTable(2);
            table5.setWidthPercentage(100f);
            float[] columnWidths1 = {0.141f, 0.565f}; // Second column will be
            // twice as first and third
            table5.setWidths(columnWidths1);

            PdfPCell cell51 = new PdfPCell(new Phrase("", fText));
            cell51.setFixedHeight(40);
            cell51.setPadding(5);
            cell51.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cell51);

            PdfPCell cell52 = new PdfPCell(new Phrase("Sub Major Head                   :", fText));
            cell52.setFixedHeight(40);
            cell52.setPadding(5);
            cell52.setHorizontalAlignment(Element.ALIGN_LEFT);
            table5.addCell(cell52);

            PdfPCell cell53 = new PdfPCell(new Phrase("", fText));
            cell53.setFixedHeight(40);
            cell53.setPadding(5);
            cell53.setHorizontalAlignment(Element.ALIGN_CENTER);
            table5.addCell(cell53);

            PdfPCell cell54 = new PdfPCell(new Phrase("Sub Minor Head                   :", fText));
            cell54.setFixedHeight(40);
            cell54.setPadding(5);
            cell54.setHorizontalAlignment(Element.ALIGN_LEFT);
            table5.addCell(cell54);

            PdfPCell cell55 = new PdfPCell(GetItextImage("कोषागार प्रमाणक क्रमांक" +
                    "दिनांक : ", true, 18));
            cell55.setFixedHeight(70);
            cell55.setPadding(10);
            cell55.setHorizontalAlignment(Element.ALIGN_LEFT);
            table5.addCell(cell55);

            PdfPCell cell56 = new PdfPCell(new Phrase("Detailed  Head                    :", fText));
            cell56.setFixedHeight(70);
            cell56.setPadding(15);
            cell56.setHorizontalAlignment(Element.ALIGN_LEFT);
            table5.addCell(cell56);

            PdfPCell cell57 = new PdfPCell(new Phrase("", russian));
            cell57.setFixedHeight(40);
            cell57.setPadding(10);
            cell57.setHorizontalAlignment(Element.ALIGN_LEFT);
            table5.addCell(cell57);

            PdfPCell cell58 = new PdfPCell(new Phrase("Detailed  Head                    : 11 / Domestic Travel Expeneces", fText));
            cell58.setFixedHeight(40);
            cell58.setPadding(10);
            cell58.setHorizontalAlignment(Element.ALIGN_LEFT);
            table5.addCell(cell58);

            PdfPCell cell59 = new PdfPCell(new Phrase("", russian));
            cell59.setFixedHeight(40);
            cell59.setPadding(5);
            cell59.setHorizontalAlignment(Element.ALIGN_LEFT);
            table5.addCell(cell59);

            PdfPCell cell60 = new PdfPCell(new Phrase("(Objection of Expenditure)", fText));
            cell60.setFixedHeight(40);
            cell60.setPadding(5);
            cell60.setHorizontalAlignment(Element.ALIGN_LEFT);
            table5.addCell(cell60);

            PdfPTable table6 = new PdfPTable(5);
            table6.setWidthPercentage(100f);
            PdfPCell cell61 = new PdfPCell(GetItextImage1("विमान/रेल्वे/आगबोट/बस \n" +
                    "भाडे", 21, 2,20));
            PdfPCell cell62 = new PdfPCell(new Paragraph("10", russian));
            PdfPCell cell63 = new PdfPCell(new Paragraph(""));
            PdfPCell cell64 = new PdfPCell(new Paragraph(""));
            PdfPCell cell65 = new PdfPCell(new Paragraph(""));
            PdfPCell cell66 = new PdfPCell(GetItextImage("किलोमीटर सडक भत्ता", false, 18));
            PdfPCell cell67 = new PdfPCell(new Paragraph("10", russian));
            PdfPCell cell68 = new PdfPCell(new Paragraph(""));
            PdfPCell cell69 = new PdfPCell(new Paragraph(""));
            PdfPCell cell70 = new PdfPCell(new Paragraph(""));
            PdfPCell cell71 = new PdfPCell(GetItextImage1("सर्वसाधारण दराने मागणी केलेल्या दैनिक भत्त्याची रक्कम", 27, 3,18));
            PdfPCell cell72 = new PdfPCell(new Paragraph("15", russian));
            PdfPCell cell73 = new PdfPCell(new Paragraph(""));
            PdfPCell cell74 = new PdfPCell(new Paragraph(""));
            PdfPCell cell75 = new PdfPCell(new Paragraph(""));
            PdfPCell cell76 = new PdfPCell(GetItextImage1("मागणी केलेल्या विशेष आणि सर्वसाधारण दर यातील फरकाची रक्कम ", 26, 3,18));
            PdfPCell cell77 = new PdfPCell(new Paragraph("18", russian));
            PdfPCell cell78 = new PdfPCell(new Paragraph(""));
            PdfPCell cell79 = new PdfPCell(new Paragraph(""));
            PdfPCell cell80 = new PdfPCell(new Paragraph(""));
            PdfPCell cell81 = new PdfPCell(GetItextImage("एकूण रक्कम रुपये ", false, 18));
            PdfPCell cell82 = new PdfPCell(new Paragraph("", russian));
            PdfPCell cell83 = new PdfPCell(new Paragraph(""));
            PdfPCell cell84 = new PdfPCell(new Paragraph(""));
            PdfPCell cell85 = new PdfPCell(new Paragraph(""));
            PdfPCell cell86 = new PdfPCell(GetItextImage("वजाती", false, 18));
            PdfPCell cell87 = new PdfPCell(new Paragraph("", russian));
            PdfPCell cell88 = new PdfPCell(new Paragraph(""));
            PdfPCell cell89 = new PdfPCell(new Paragraph(""));
            PdfPCell cell90 = new PdfPCell(new Paragraph(""));
            PdfPCell cell91 = new PdfPCell(GetItextImage("1.प्रवास भत्ता अग्रीम ", false, 18));
            PdfPCell cell92 = new PdfPCell(new Paragraph("", russian));
            PdfPCell cell93 = new PdfPCell(new Paragraph(""));
            PdfPCell cell94 = new PdfPCell(new Paragraph(""));
            PdfPCell cell95 = new PdfPCell(new Paragraph(""));
            PdfPCell cell96 = new PdfPCell(GetItextImage("2.स्थायी प्रवास भत्ता अग्रीम", false, 18));
            PdfPCell cell97 = new PdfPCell(new Paragraph("", russian));
            PdfPCell cell98 = new PdfPCell(new Paragraph(""));
            PdfPCell cell99 = new PdfPCell(new Paragraph(""));
            PdfPCell cell100 = new PdfPCell(new Paragraph(""));
            PdfPCell cell101 = new PdfPCell(GetItextImage("3.वाहन भत्ता ", false, 18));
            PdfPCell cell102 = new PdfPCell(new Paragraph("", russian));
            PdfPCell cell103 = new PdfPCell(new Paragraph(""));
            PdfPCell cell104 = new PdfPCell(new Paragraph(""));
            PdfPCell cell105 = new PdfPCell(new Paragraph(""));
            PdfPCell cell106 = new PdfPCell(GetItextImage("एकूण वजाती रुपये ", false, 18));
            PdfPCell cell107 = new PdfPCell(new Paragraph("", russian));
            PdfPCell cell108 = new PdfPCell(new Paragraph(""));
            PdfPCell cell109 = new PdfPCell(new Paragraph(""));
            PdfPCell cell110 = new PdfPCell(new Paragraph(""));
            PdfPCell cell111 = new PdfPCell(GetItextImage1("शेवटच्या पानावर घेतलेली निव्वळ देय रक्कम ", 23, 2,18));
            PdfPCell cell112 = new PdfPCell(new Paragraph("", russian));
            PdfPCell cell113 = new PdfPCell(new Paragraph(""));
            PdfPCell cell114 = new PdfPCell(new Paragraph(""));
            PdfPCell cell115 = new PdfPCell(new Paragraph(""));

            cell61.setFixedHeight(60);
            cell61.setPadding(5);
            cell61.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell61);

            cell62.setFixedHeight(60);
            cell62.setPadding(10);
            cell62.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell62);

            cell63.setFixedHeight(60);
            cell63.setPadding(5);
            cell63.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell63);

            cell64.setFixedHeight(60);
            cell64.setPadding(5);
            cell64.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell64);

            cell65.setFixedHeight(60);
            cell65.setPadding(5);
            cell65.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell65);

            cell66.setFixedHeight(40);
            cell66.setPadding(5);
            cell66.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell66);

            cell67.setFixedHeight(40);
            cell67.setPadding(5);
            cell67.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell67);

            cell68.setFixedHeight(40);
            cell68.setPadding(5);
            cell68.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell68);

            cell69.setFixedHeight(40);
            cell69.setPadding(5);
            cell69.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell69);

            cell70.setFixedHeight(40);
            cell70.setPadding(5);
            cell70.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell70);

            cell71.setFixedHeight(70);
            cell71.setPadding(5);
            cell71.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell71);

            cell72.setFixedHeight(60);
            cell72.setPadding(10);
            cell72.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell72);

            cell73.setFixedHeight(60);
            cell73.setPadding(5);
            cell73.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell73);

            cell74.setFixedHeight(60);
            cell74.setPadding(5);
            cell74.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell74);

            cell75.setFixedHeight(60);
            cell75.setPadding(5);
            cell75.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell75);

            cell76.setFixedHeight(70);
            cell76.setPadding(5);
            cell76.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell76);

            cell77.setFixedHeight(60);
            cell77.setPadding(10);
            cell77.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell77);

            cell78.setFixedHeight(60);
            cell78.setPadding(5);
            cell78.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell78);

            cell79.setFixedHeight(60);
            cell79.setPadding(5);
            cell79.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell79);

            cell80.setFixedHeight(60);
            cell80.setPadding(5);
            cell80.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell80);

            cell81.setFixedHeight(40);
            cell81.setPadding(5);
            cell81.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell81);

            cell82.setFixedHeight(40);
            cell82.setPadding(5);
            cell82.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell82);

            cell83.setFixedHeight(40);
            cell83.setPadding(5);
            cell83.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell83);

            cell84.setFixedHeight(40);
            cell84.setPadding(5);
            cell84.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell84);

            cell85.setFixedHeight(40);
            cell85.setPadding(5);
            cell85.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell85);

            cell86.setFixedHeight(40);
            cell86.setPadding(5);
            cell86.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell86);

            cell87.setFixedHeight(40);
            cell87.setPadding(5);
            cell87.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell87);

            cell88.setFixedHeight(40);
            cell88.setPadding(5);
            cell88.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell88);

            cell89.setFixedHeight(40);
            cell89.setPadding(5);
            cell89.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell89);

            cell90.setFixedHeight(40);
            cell90.setPadding(5);
            cell90.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell90);

            cell91.setFixedHeight(40);
            cell91.setPadding(5);
            cell91.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell91);

            cell92.setFixedHeight(40);
            cell92.setPadding(5);
            cell92.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell92);

            cell93.setFixedHeight(40);
            cell93.setPadding(5);
            cell93.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell93);

            cell94.setFixedHeight(40);
            cell94.setPadding(5);
            cell94.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell94);

            cell95.setFixedHeight(40);
            cell95.setPadding(5);
            cell95.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell95);

            cell96.setFixedHeight(40);
            cell96.setPadding(5);
            cell96.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell96);

            cell97.setFixedHeight(40);
            cell97.setPadding(5);
            cell97.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell97);

            cell98.setFixedHeight(40);
            cell98.setPadding(5);
            cell98.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell98);

            cell99.setFixedHeight(40);
            cell99.setPadding(5);
            cell99.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell99);

            cell100.setFixedHeight(40);
            cell100.setPadding(5);
            cell100.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell100);

            cell101.setFixedHeight(40);
            cell101.setPadding(5);
            cell101.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell101);

            cell102.setFixedHeight(40);
            cell102.setPadding(5);
            cell102.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell102);

            cell103.setFixedHeight(40);
            cell103.setPadding(5);
            cell103.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell103);

            cell104.setFixedHeight(40);
            cell104.setPadding(5);
            cell104.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell104);

            cell105.setFixedHeight(40);
            cell105.setPadding(5);
            cell105.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell105);

            cell106.setFixedHeight(40);
            cell106.setPadding(5);
            cell106.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell106);

            cell107.setFixedHeight(40);
            cell107.setPadding(5);
            cell107.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell107);

            cell108.setFixedHeight(40);
            cell108.setPadding(5);
            cell108.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell108);

            cell109.setFixedHeight(40);
            cell109.setPadding(5);
            cell109.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell109);

            cell110.setFixedHeight(40);
            cell110.setPadding(5);
            cell110.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell110);

            cell111.setFixedHeight(60);
            cell111.setPadding(5);
            cell111.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell111);

            cell112.setFixedHeight(60);
            cell112.setPadding(5);
            cell112.setHorizontalAlignment(Element.ALIGN_CENTER);
            table6.addCell(cell112);

            cell113.setFixedHeight(60);
            cell113.setPadding(5);
            cell113.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell113);

            cell114.setFixedHeight(60);
            cell114.setPadding(5);
            cell114.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell114);

            cell115.setFixedHeight(60);
            cell115.setPadding(5);
            cell115.setHorizontalAlignment(Element.ALIGN_LEFT);
            table6.addCell(cell115);

            // Add table in document
            document.add(table);
            document.add(table2);
            document.add(table3);
            document.add(table4);
            document.add(table5);
            document.add(table6);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }
    //End of 1st page 1st table

    //2nd page 1 st table
    public void createTitles(String[] shorttext) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(shorttext.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < shorttext.length) {
                pdfPCell = new PdfPCell(GetItextImage(shorttext[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.newPage();
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createSubTitles(String[] SubTitles) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian1);
            PdfPTable pdfPTable = new PdfPTable(SubTitles.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < SubTitles.length) {
                pdfPCell = new PdfPCell(GetItextImage(SubTitles[indexC++], false, 40));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createSubHeadTitle(String[] SubHeadTitle) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(SubHeadTitle.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < SubHeadTitle.length) {
                pdfPCell = new PdfPCell(GetItextImage(SubHeadTitle[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createPriceandmonth(String[] Priceandmonth) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(Priceandmonth.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < Priceandmonth.length) {
                pdfPCell = new PdfPCell(GetItextImage(Priceandmonth[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    private void addChildP(Paragraph childParagraph) {
        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);
    }

    private void addChildP1(Image childParagraph) {
        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);
    }

    public void addSign(String name, String desi) {
        try {

            paragraph = new Paragraph("");//name);
            addChildP1(GetItextImage(name, false, 18));
            addChildP1(GetItextImage(desi, false, 18));
            paragraph.setSpacingBefore(35);
            //addChildP(new Paragraph("Generado:"+date,fHighText));
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("Open Document", e.toString());
        }
    }

    public void addParagraph(String text) {
        try {
            paragraph = new Paragraph(text, russian);
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("AddParagraph", e.toString());
        }
    }

    //Table Sub Sub header
    public void createHeader2(String[] header2) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable table = new PdfPTable(5); // Create 3 columns in table.

            // Set table Width as 100%
            table.setWidthPercentage(100f);

            // Set Column widths of table
            float[] columnWidths = {1.04f, 0.41f, 0.28f, 0.33f, 0.34f}; // Second column will be
            // twice as first and third
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(GetItextImage("प्रवासाचा आणि मुख्यालयाचा तपशील", false, 18));
            PdfPCell cell3 = new PdfPCell(new Paragraph(""));
            PdfPCell cell4 = new PdfPCell(GetItextImage("वाहन भाडे", false, 18));
            PdfPCell cell5 = new PdfPCell(GetItextImage("मुख्य.अनुपस्थिती", false, 18));
            PdfPCell cell6 = new PdfPCell(new Paragraph(""));

            cell1.setFixedHeight(40);
            cell1.setPadding(5);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

            cell3.setFixedHeight(40);
            cell3.setPadding(5);

            cell4.setFixedHeight(40);
            cell4.setPadding(5);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);

            cell5.setFixedHeight(40);
            cell5.setPadding(5);
            cell5.setHorizontalAlignment(Element.ALIGN_CENTER);

            cell6.setFixedHeight(40);
            cell6.setPadding(5);

            table.addCell(cell1);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);

            document.add(table);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    //Table Sub Header
    public void createHeader(String[] header1) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable table = new PdfPTable(6); // Create 3 columns in table.

            // Set table Width as 100%
            table.setWidthPercentage(100f);

            // Set Column widths of table
            float[] columnWidths = {0.52f, 0.52f, 0.41f, 0.28f, 0.33f, 0.34f}; // Second column will be
            // twice as first and third
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("गमन", russian));
            PdfPCell cell2 = new PdfPCell(new Paragraph("आगमन", russian));
            PdfPCell cell3 = new PdfPCell(new Paragraph(""));
            PdfPCell cell4 = new PdfPCell(new Paragraph(""));
            PdfPCell cell5 = new PdfPCell(new Paragraph(""));
            PdfPCell cell6 = new PdfPCell(new Paragraph(""));

            cell1.setFixedHeight(40);
            cell1.setPadding(5);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

            cell2.setFixedHeight(40);
            cell2.setPadding(5);
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

            cell3.setFixedHeight(40);
            cell3.setPadding(5);

            cell4.setFixedHeight(40);
            cell4.setPadding(5);

            cell5.setFixedHeight(40);
            cell5.setPadding(5);

            cell6.setFixedHeight(40);
            cell6.setPadding(5);

            table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);

            document.add(table);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    //For table
    public void createTable(String[] TAheader, ArrayList<String[]> clients, String[] numbers) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(TAheader.length);
            pdfPTable.setWidthPercentage(100f);
            float[] columnWidths = {0.18f, 0.21f, 0.13f, 0.18f, 0.21f, 0.13f, 0.15f, 0.26f, 0.13f, 0.15f, 0.15f, 0.18f, 0.34f};
            pdfPTable.setWidths(columnWidths);
            PdfPCell pdfPCell;
            int indexC = 0;
            int indexC1 = 0;
            final Double Exp;
            while (indexC < TAheader.length) {
                if (indexC == 0){
                    pdfPCell = new PdfPCell(GetItextImage1(TAheader[indexC++],5,4,18));
                }
                else if (indexC == 6) {
                    pdfPCell = new PdfPCell(GetItextImage1(TAheader[indexC++], 6, 6,18));
                } else if (indexC == 7) {
                    pdfPCell = new PdfPCell(GetItextImage1(TAheader[indexC++], 15, 6,18));
                    // pdfPCell = new PdfPCell(GetItextImage(TAheader[indexC++],false));
                } else if (indexC == 11) {
                    pdfPCell = new PdfPCell(GetItextImage1(TAheader[indexC++], 7, 6,18));
                } else if (indexC == 12) {
                    pdfPCell = new PdfPCell(GetItextImage1(TAheader[indexC++], 15, 6,18));
                } else {
                    pdfPCell = new PdfPCell(GetItextImage(TAheader[indexC++], true, 18));
                }

                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(100);
                pdfPTable.addCell(pdfPCell);
            }

            while (indexC1 < numbers.length) {
                pdfPCell = new PdfPCell(GetItextImage(numbers[indexC1++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPTable.addCell(pdfPCell);
            }

            for (int indexR = 0; indexR < clients.size(); indexR++) {
                String[] row = clients.get(indexR);

                for (indexC = 0; indexC < TAheader.length; indexC++) {
                    /*if(indexC == 1 ) {
                        pdfPCell = new PdfPCell(GetItextImage1(TAheader[indexC++], 12, 2));
                    }
                    else {
                         pdfPCell = new PdfPCell(GetItextImage(TAheader[indexC++],false));
                    }*/
                    if (row[indexC] == null)
                        row[indexC] = "";

                    if (row[indexC] != null && !row[indexC].isEmpty() && !row[indexC].equals("null")) {
                        if(indexC == 0 ) {
                            pdfPCell = new PdfPCell(GetItextImage1(row[indexC], 9, 3,18));
                        }
                        else if (indexC == 3){
                            pdfPCell = new PdfPCell(GetItextImage1(row[indexC],9,3,18));
                        }
                        else if (indexC == 7){
                            pdfPCell = new PdfPCell(GetItextImage1(row[indexC],15,3,18));
                        }
                        else {
                            pdfPCell = new PdfPCell(GetItextImage(row[indexC], true, 18));
                        }
                        //pdfPCell = new PdfPCell(GetItextImage(row[indexC], true, 18));
                        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfPCell.setPadding(5);
                        pdfPCell.setFixedHeight(60);
                        pdfPTable.addCell(pdfPCell);
                    } else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC], russian));
                        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfPCell.setPadding(5);
                        pdfPCell.setFixedHeight(60);
                        pdfPTable.addCell(pdfPCell);
                    }
                }
                if (!row[10].equals("") && Integer.parseInt(row[10]) > 12) {
                    fields.add(indexR);
                }


            }

            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void addTitles() {
        try {
            Paragraph paragraph = new Paragraph();
            Paragraph paragraph2 = new Paragraph();
            Paragraph paragraph3 = new Paragraph();
            Paragraph paragraph4 = new Paragraph();
            Paragraph paragraph5 = new Paragraph();

            paragraph.setSpacingBefore(300);
            paragraph2.setSpacingBefore(5);
            paragraph3.setSpacingBefore(5);
            paragraph4.setSpacingBefore(5);
            paragraph5.setSpacingBefore(5);

            Image c = GetItextImage("प्रमाणपत्र :", false, 18);
            paragraph.add(c);

            Image c1 = GetItextImage("1. प्रमाणित करण्यात येते की , सदर देयक मी यापुर्वी सादर केलेले नाही .  \n", false, 18);
            paragraph2.add(c1);
            paragraph2.setAlignment(Element.ALIGN_LEFT);

            Image c2 = GetItextImage("2. प्रमाणित करण्यात येते की , सदर प्रवासाकरीता मी आगाऊ अग्रीम घेतलेले नाही . \n", false, 18);
            paragraph3.add(c2);

            Image c3 = GetItextImage("3. प्रमाणित करण्यात येते की , सदर प्रवास हा फक्त शासकीय कामा करीत केलेला आहे .  \n", false, 18);
            paragraph4.add(c3);

            Image c4 = GetItextImage("4. प्रमाणित करण्यात येते की , सदर प्रवास भत्ता देयकात दर्शविण्यात आलेले अंतर , वेळ व प्रवास भाडे माझ्या माहिती प्रमाणे बरोबर आहे .   \n", false, 18);
            paragraph5.add(c4);

            document.add(paragraph);
            document.add(paragraph2);
            document.add(paragraph3);
            document.add(paragraph4);
            document.add(paragraph5);
        } catch (Exception e) {
            Log.e("Open Document", e.toString());
        }
    }

    public void AddSpace() {
        try {
            paragraph = new Paragraph("");
            paragraph.setAlignment(Element.ALIGN_RIGHT);
            paragraph.setSpacingBefore(35);
            document.add(paragraph);
        }catch (Exception e)
        {

        }
    }

    public void addTitles1() {
        try {
            Paragraph paragraph = new Paragraph();
            Paragraph paragraph2 = new Paragraph();
            paragraph.setSpacingBefore(300);
            paragraph2.setSpacingBefore(5);

            Image c = GetItextImage("राजपत्रित अधिकाऱ्याची / आहरण व सवितरण\n", false, 18);
            c.setAlignment(Element.ALIGN_CENTER);
            paragraph.add(c);

            Image c1 = GetItextImage("अधिकाऱ्याची स्वाक्षरी व पदनाम           \n", false, 18);
            paragraph2.add(c1);
            c1.setAlignment(Element.ALIGN_CENTER);

            document.add(paragraph);
            document.add(paragraph2);

        } catch (Exception e) {
            Log.e("Open Document", e.toString());
        }
    }

    //next page table start
    public void createTable2(String[] TBheader, ArrayList<String[]> clients, String[] numbers2) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(TBheader.length);
            pdfPTable.setWidthPercentage(100);
            float[] columnWidths = {0.15f, 0.15f, 0.15f, 0.15f, 0.15f, 0.15f, 0.58f, 0.13f};
            pdfPTable.setWidths(columnWidths);

            PdfPCell pdfPCell;
            int indexC = 0;
            int indexC1 = 0;

            while (indexC < TBheader.length) {
                if (indexC == 0) {
                    pdfPCell = new PdfPCell(GetItextImage1(TBheader[indexC++], 11, 6,18));
                } else if (indexC == 1) {
                    pdfPCell = new PdfPCell(GetItextImage1(TBheader[indexC++], 11, 6,18));
                } else if (indexC == 2) {
                    pdfPCell = new PdfPCell(GetItextImage1(TBheader[indexC++], 10, 6,18));
                } else if (indexC == 3) {
                    pdfPCell = new PdfPCell(GetItextImage1(TBheader[indexC++], 10, 6,18));
                } else if (indexC == 4) {
                    pdfPCell = new PdfPCell(GetItextImage1(TBheader[indexC++], 10, 6,18));
                } else if (indexC == 5) {
                    pdfPCell = new PdfPCell(GetItextImage1(TBheader[indexC++], 10, 6,18));
                } else if (indexC == 6) {
                    pdfPCell = new PdfPCell(GetItextImage1(TBheader[indexC++], 40, 2,18));
                } else {
                    pdfPCell = new PdfPCell(GetItextImage(TBheader[indexC++], true, 18));
                }

                //pdfPCell = new PdfPCell(new Phrase(TBheader[indexC++], russian));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPTable.addCell(pdfPCell);
            }
            while (indexC1 < numbers2.length) {
                pdfPCell = new PdfPCell(GetItextImage(numbers2[indexC1++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPTable.addCell(pdfPCell);
            }

            for (int indexR = 0; indexR < clients.size(); indexR++) {

                String[] row = clients.get(indexR);

                if (row[indexR] == null)
                    row[indexR] = "";

                if (fields.contains(indexR) || row[0].equals("")) {

                    row[1] = row[0];
                } else {
                    int res;
                    res = Integer.parseInt(row[0]) / 2;
                    String res1 = Integer.toString(res);
                    row[1] = res1;
                }

                for (indexC = 0; indexC < TBheader.length; indexC++) {
                    if (row[indexC] == null)
                        row[indexC] = "";

                    if (!row[5].equals("") && indexC == 5) {
                        Double add;
                        add = Double.valueOf(row[1]) + Double.valueOf(row[indexC]);
                        String add1 = Double.toString(add);
                        row[indexC] = add1;

                    }

                    if (row[indexC] != null && !row[indexC].isEmpty() && !row[indexC].equals("null")) {

                        if (indexC == 6){
                            pdfPCell = new PdfPCell(GetItextImage1(row[indexC],50,3,22));
                        }
                        else
                        {
                            pdfPCell = new PdfPCell(GetItextImage(row[indexC],true,18));
                        }

                        //pdfPCell = new PdfPCell(GetItextImage(row[indexC], true, 18));
                        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfPCell.setPadding(5);
                        pdfPCell.setFixedHeight(60);
                        pdfPTable.addCell(pdfPCell);
                    } else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC], russian));
                        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfPCell.setPadding(5);
                        pdfPCell.setFixedHeight(60);
                        pdfPTable.addCell(pdfPCell);
                    }

                }
                if (!row[5].equals(""))
                    TotalAmt = (TotalAmt) + Double.parseDouble(row[5]);// Double.valueOf(row[5]);

            }
            paragraph.add(pdfPTable);
            PdfPTable table2 = new PdfPTable(7);
            table2.setWidthPercentage(100f);
            table2.setHorizontalAlignment(table2.ALIGN_LEFT);
            float[] columnWidths1 = {0.15f , 0.15f , 0.15f, 0.3f, 0.15f , 0.58f , 0.13f};
            table2.setWidths(columnWidths1);
            PdfPCell cell1 = new PdfPCell((new Paragraph("")));
            PdfPCell cell2 = new PdfPCell((new Paragraph("")));
            PdfPCell cell21 = new PdfPCell(new Paragraph(""));
            PdfPCell cell22 = new PdfPCell(GetItextImage("एकूण रक्कम", false, 18));
            PdfPCell cell23 = new PdfPCell(GetItextImage(TotalAmt.toString(), false, 18));
            PdfPCell cell3 = new PdfPCell((new Paragraph("")));
            PdfPCell cell4 = new PdfPCell((new Paragraph("")));

            cell1.setFixedHeight(40);
            cell1.setBorder(Rectangle.TOP);
            table2.addCell(cell1);

            cell2.setFixedHeight(40);
            cell2.setBorder(Rectangle.TOP);
            table2.addCell(cell2);

            cell21.setFixedHeight(40);
            cell21.setPadding(5);
            cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
            table2.addCell(cell21);

            cell22.setFixedHeight(40);
            cell22.setPadding(5);
            cell22.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(cell22);

            cell23.setFixedHeight(40);
            cell23.setPadding(5);
            cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
            table2.addCell(cell23);

            cell3.setFixedHeight(40);
            cell3.setBorder(Rectangle.TOP);
            table2.addCell(cell3);

            cell4.setFixedHeight(40);
            cell4.setBorder(Rectangle.TOP);
            table2.addCell(cell4);

            PdfPTable table3 = new PdfPTable(6);
            table3.setWidthPercentage(100f);
            table3.setHorizontalAlignment(table3.ALIGN_LEFT);
            float[] columnWidths2 = {0.15f , 0.15f , 0.45f, 0.15f , 0.58f , 0.13f};
            table3.setWidths(columnWidths2);

            PdfPCell cell6 = new PdfPCell((new Paragraph("")));
            PdfPCell cell7 = new PdfPCell((new Paragraph("")));
            PdfPCell cell31 = new PdfPCell(GetItextImage("निव्वळ देय रक्कम रुपये", false, 18));
            PdfPCell cell32 = new PdfPCell(GetItextImage(TotalAmt.toString(), false, 18));
            PdfPCell cell8 = new PdfPCell((new Paragraph("")));
            PdfPCell cell9 = new PdfPCell((new Paragraph("")));

            cell6.setFixedHeight(40);
            cell6.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell6);

            cell7.setFixedHeight(40);
            cell7.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell7);

            cell31.setFixedHeight(40);
            cell31.setPadding(5);
            cell31.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell31);

            cell32.setFixedHeight(40);
            cell32.setPadding(5);
            cell32.setHorizontalAlignment(Element.ALIGN_CENTER);
            table3.addCell(cell32);

            cell8.setFixedHeight(40);
            cell8.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell8);

            cell9.setFixedHeight(40);
            cell9.setBorder(Rectangle.NO_BORDER);
            table3.addCell(cell9);


            document.add(paragraph);
            document.add(table2);
            document.add(table3);

        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createSubTitles2(String[] SubTitles2) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(SubTitles2.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < SubTitles2.length) {
                pdfPCell = new PdfPCell(GetItextImage(SubTitles2[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createSubTitles3(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian1);
            PdfPTable pdfPTable = new PdfPTable(SubTitles3.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < SubTitles3.length) {
                pdfPCell = new PdfPCell(new Phrase(SubTitles3[indexC++], russian));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createSubTitles4(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian1);
            PdfPTable pdfPTable = new PdfPTable(SubTitles3.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < SubTitles3.length) {
                pdfPCell = new PdfPCell(new Phrase(SubTitles3[indexC++], russian));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createSubTitles5(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian1);
            PdfPTable pdfPTable = new PdfPTable(SubTitles3.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < SubTitles3.length) {
                pdfPCell = new PdfPCell(new Phrase(SubTitles3[indexC++], russian));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createTitles2(String[] shorttext) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(shorttext.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < shorttext.length) {
                pdfPCell = new PdfPCell(GetItextImage(shorttext[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.newPage();
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }
    //next page table end

    //3rd page 1st table start
    public void createthreerdpageheder(String[] threerdpageheder) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(threerdpageheder.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < threerdpageheder.length) {
                pdfPCell = new PdfPCell(GetItextImage(threerdpageheder[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.newPage();
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createthreerdpageheder2(String[] threerdpageheder2) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(threerdpageheder2.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < threerdpageheder2.length) {
                pdfPCell = new PdfPCell(GetItextImage(threerdpageheder2[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createthreerdpageheder3(String[] threerdpageheder3) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(threerdpageheder3.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < threerdpageheder3.length) {
                pdfPCell = new PdfPCell(GetItextImage(threerdpageheder3[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createhead(String[] head) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(head.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < head.length) {
                pdfPCell = new PdfPCell(GetItextImage(head[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createthreerdpageheder4(String[] threerdpageheder4) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(threerdpageheder4.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < threerdpageheder4.length) {
                pdfPCell = new PdfPCell(GetItextImage1(threerdpageheder4[indexC++], 133, 3,18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(90);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void creategap(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable table = new PdfPTable(1); // Create 3 columns in table.

            // Set table Width as 100%
            table.setWidthPercentage(100f);

            // Set Column widths of table
            float[] columnWidths = {0.543f}; // Second column will be
            // twice as first and third
            table.setWidths(columnWidths);

            PdfPCell cell1 = new PdfPCell(new Paragraph("", russian));

            cell1.setFixedHeight(40);
            cell1.setPadding(5);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

            table.addCell(cell1);

            document.add(table);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createthreeheader5(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100f);
            PdfPCell cell = new PdfPCell(GetItextImage("ठिकाण:", false, 18));
            cell.setFixedHeight(40);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            PdfPCell cell23 = new PdfPCell(GetItextImage("आहरण व सवितरण अधिकारी यांची स्वाक्षर", false, 18));
            cell23.setColspan(2);
            cell23.setRowspan(2);
            cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell23);

            cell = new PdfPCell(GetItextImage("दिनांक:", false, 18));
            cell.setFixedHeight(40);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            document.add(table);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createhead2(String[] head2) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(head2.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < head2.length) {
                pdfPCell = new PdfPCell(GetItextImage(head2[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPCell.setFixedHeight(40);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createthreeheader6(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100f);
            // Create cells
            PdfPCell cell1 = new PdfPCell(GetItextImage("वर्ष 2018-19 करीता अर्थसंकल्पीय तरतुद रुपये", false, 18));
            PdfPCell cell2 = new PdfPCell(new Paragraph(""));
            PdfPCell cell3 = new PdfPCell(GetItextImage("हे देयक धरुन झालेला खर्च", false, 18));
            PdfPCell cell4 = new PdfPCell(new Paragraph(""));
            PdfPCell cell5 = new PdfPCell(GetItextImage("शिल्लक तरतुद", false, 18));
            PdfPCell cell6 = new PdfPCell(new Paragraph(""));

            // Add cells in table
            cell1.setFixedHeight(40);
            cell1.setPadding(5);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell1);

            cell2.setFixedHeight(40);
            cell2.setPadding(5);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell2);

            cell3.setFixedHeight(40);
            cell3.setPadding(5);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell3);

            cell4.setFixedHeight(40);
            cell4.setPadding(5);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell4);

            cell5.setFixedHeight(40);
            cell5.setPadding(5);
            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell5);

            cell5.setFixedHeight(40);
            cell5.setPadding(5);
            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell6);

            // Add table in document
            document.add(table);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createthreeheader7(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);

            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100f);
            // Create cells
            PdfPCell cell1 = new PdfPCell(GetItextImage("रुपये                 /-    रुपये", false, 18));
            PdfPCell cell2 = new PdfPCell(GetItextImage("प्रमाणित करण्यात येते की , मी सर्व प्रकारची विहित तपासणी केली आहे.", false, 18));

            // Add cells in table
            cell1.setFixedHeight(40);
            cell1.setPadding(5);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell1);

            cell2.setFixedHeight(40);
            cell2.setPadding(5);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell2);

            // Add table in document
            document.add(table);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }
    //ens 3rd 1st table

    //3rd page 2nd table
    public void createthreeheader8(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);

            paragraph.setSpacingAfter(30);
            paragraph.setSpacingBefore(30);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100f);
            PdfPCell cell = new PdfPCell(GetItextImage("ठिकाण:", false, 18));
            PdfPCell cell4 = new PdfPCell(new Paragraph(""));
            PdfPCell cell2 = new PdfPCell(GetItextImage("दिनांक:", false, 18));
            PdfPCell cell1 = new PdfPCell(GetItextImage("नियंत्रण अधिकाऱ्याची स्वाक्षरी आणि पदनाम :", false, 18));

            cell.setFixedHeight(40);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell4.setFixedHeight(40);
            cell4.setPadding(5);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell4);

            cell2.setFixedHeight(40);
            cell2.setPadding(5);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell2);


            cell1.setFixedHeight(40);
            cell1.setPadding(5);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);

            document.add(paragraph);
            document.add(table);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createthreeheader9(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);

            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100f);
            // Create cells
            PdfPCell cell1 = new PdfPCell(GetItextImage("कोषागाराकरिता ", false, 18));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Pay Rs...................................... " +
                    "In Words Rs............................................................................", fText));

            // Add cells in table
            cell1.setFixedHeight(40);
            cell1.setPadding(5);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell1);

            cell2.setFixedHeight(40);
            cell2.setPadding(5);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell2);

            // Add table in document
            document.add(table);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createthreeheader10(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100f);
            PdfPCell cell = new PdfPCell(new Paragraph("Accountant:", fText));
            PdfPCell cell4 = new PdfPCell(new Paragraph("Treasury Officer/Assiostant pa and Accounts officer", fText));
            PdfPCell cell2 = new PdfPCell(new Paragraph(""));
            PdfPCell cell1 = new PdfPCell(new Paragraph("Date:", fText));

            cell.setFixedHeight(40);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell4.setFixedHeight(40);
            cell4.setPadding(5);
            cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell4);

            cell2.setFixedHeight(40);
            cell2.setPadding(5);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell2);


            cell1.setFixedHeight(40);
            cell1.setPadding(5);
            cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell1);

            document.add(table);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createthreeheader11(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);

            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100f);
            // Create cells
            PdfPCell cell1 = new PdfPCell(new Paragraph("FOR ACCOUNTANT GENERALS OFFICE", fText));
            PdfPCell cell2 = new PdfPCell(new Paragraph("Admitted for rs.                                          " +
                    "                                                     Objected to Rs.", fText));
            PdfPCell cell13 = new PdfPCell(new Paragraph("Reasons for objection", fText));

            // Add cells in table
            cell1.setFixedHeight(40);
            cell1.setPadding(5);
            cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell1);

            cell2.setFixedHeight(40);
            cell2.setPadding(5);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell2);

            cell13.setFixedHeight(40);
            cell13.setPadding(5);
            cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell13);

            // Add table in document
            document.add(table);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createthreeheader12(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100f);
            PdfPCell cell = new PdfPCell(new Paragraph(""));
            PdfPCell cell2 = new PdfPCell(new Paragraph(""));
            PdfPCell cell3 = new PdfPCell(new Paragraph(""));
            PdfPCell cell4 = new PdfPCell(new Paragraph(""));
            PdfPCell cell5 = new PdfPCell(new Paragraph(""));

            cell.setFixedHeight(40);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            cell2.setFixedHeight(40);
            cell2.setPadding(5);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell2);

            cell3.setFixedHeight(40);
            cell3.setPadding(5);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell3);

            cell4.setFixedHeight(40);
            cell4.setPadding(5);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell4);

            cell5.setFixedHeight(40);
            cell5.setPadding(5);
            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell5);

            document.add(table);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createthreeheader13(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();

            PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(100f);
            float[] columnWidths = {0.122f, 0.122f, 0.365f}; // Second column will be
            // twice as first and third
            table.setWidths(columnWidths);
            PdfPCell cell = new PdfPCell(new Phrase("Auditor", fText));
            cell.setFixedHeight(40);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Section Officer:", fText));
            cell.setFixedHeight(40);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            PdfPCell cell23 = new PdfPCell(new Phrase("Reviewing Officer", fText));
            cell23.setColspan(2);
            cell23.setRowspan(2);
            cell23.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell23);

            cell = new PdfPCell(new Phrase("section Officer:", fText));
            cell.setFixedHeight(40);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);

            document.add(table);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createthreeheader14(String[] SubTitles3) {
        try {
            paragraph = new Paragraph();

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100f);
            PdfPCell cell = new PdfPCell(new Paragraph("Retrenchment slip No.", fText));
            PdfPCell cell2 = new PdfPCell(new Paragraph(""));
            PdfPCell cell3 = new PdfPCell(new Paragraph(""));
            PdfPCell cell4 = new PdfPCell(new Paragraph(""));
            PdfPCell cell5 = new PdfPCell(new Paragraph(""));

            cell.setFixedHeight(40);
            cell.setPadding(5);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            table.addCell(cell2);

            cell2.setFixedHeight(40);
            cell2.setPadding(5);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell2);
            table.addCell(cell2);

            cell3.setFixedHeight(40);
            cell3.setPadding(5);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell3);
            table.addCell(cell3);

            cell4.setFixedHeight(40);
            cell4.setPadding(5);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell4);
            table.addCell(cell4);

            cell5.setFixedHeight(40);
            cell5.setPadding(5);
            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell5);
            table.addCell(cell5);

            document.add(table);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createwatermark(String[] SubTitles3) {
        try {
            Paragraph paragraph = new Paragraph();
            Anchor anchor = new Anchor(
                    "App Designed by XDTPL", fText);
            anchor.setReference(
                    "http://xdtpl.com/");

            paragraph.setAlignment(Element.ALIGN_RIGHT);

            paragraph.setSpacingBefore(300);
            paragraph.add(anchor);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }
    //3rd page 2nd table

    class MyFooter extends PdfPageEventHelper {

        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, footer(), (document.right() - document.rightMargin()) / 1
                    + document.rightMargin(), document.bottom() + 1, 0);

        }

        private Phrase footer() {
            Phrase p = new Phrase("SMART Govt. Diary App", fText);
            return p;
        }
    }


    public void viewPDF(Context ctx) {
        File folder = new File(Environment.getExternalStorageDirectory().toString(), "PDF");
        // Intent intent = new Intent(context,TABillActivity.class);
        //File pdfFile1 = new File(folder,"TemplatePDF.pdf");
        //  intent.putExtra("Path", pdfFile1.getPath());
        // Toast.makeText(ctx, (pdfFile1.getPath()), Toast.LENGTH_LONG).show();
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //context.startActivity(intent);
    }

    public Bitmap textAsBitmap(String text, float textSize, int textColor, boolean isSplit) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.5f); // round

        if (isSplit) {
            int height = (int) (baseline + paint.descent() + 15.5f);
            Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(image);
            try {
                int txtlen1, txtlen2 = 0;
                txtlen1 = text.length();
                if (txtlen1 > 16) {
                    txtlen1 = 16;
                    txtlen2 = text.length() - txtlen1;
                }
                if (txtlen2 > 25)
                    txtlen2 = 25;

                String text1 = text.substring(0, txtlen1);
                canvas.drawText(text1, 0, baseline, paint);
                String text2 = "";
                if (txtlen2 > 0) {
                    text2 = text.substring((txtlen1), (txtlen1 + txtlen2));
                    canvas.drawText(text2, 0, (baseline + 18), paint);
                }
            } catch (Exception e) {
                //Toast.makeText(, e.getMessage(),Toast.LENGTH_LONG).show();
                System.out.println(e.getMessage());
            }
            return image;
        } else {
            int height = (int) (baseline + paint.descent() + 0.7f);
            Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(image);
            canvas.drawText(text, 0, baseline, paint);
            return image;
        }
    }

    public Bitmap textAsBitmap1(String text, float textSize, int textColor, int wdth, int rowcount) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.5f); // round


        int height = (int) (baseline + paint.descent() + 15.5f);
        if (rowcount == 3)
            height = (int) (baseline + paint.descent() + 35.5f);
        if (rowcount == 4)
            height = (int) (baseline + paint.descent() + 55.5f);
        if (rowcount == 6)
            height = (int) (baseline + paint.descent() + 75.5f);

        Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(image);
        try {
            int len = 0, bsline = 0;
            int cntCounted = 0;
            int colWidth = wdth;
            int lth = text.length();
            for (int i = 0; i < rowcount; i++) {
                if (text.length() < wdth)
                    colWidth = text.length();

                if (text.length() < (wdth * (cntCounted + 1)) && cntCounted != 0)
                    colWidth = (lth - (wdth * cntCounted));

                String text1 = text.substring(len, (len + colWidth));
                canvas.drawText(text1, 0, baseline, paint);
                len = len + wdth;
                baseline = (baseline + 18);
                cntCounted++;
            }
        } catch (Exception e) {
            //Toast.makeText(, e.getMessage(),Toast.LENGTH_LONG).show();
            System.out.println(e.getMessage());
        }
        return image;
    }
}
