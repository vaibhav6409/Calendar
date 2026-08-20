package com.paviso.govtcalenders;

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
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class TemplatePDF {
    private Context context;
    private File pdfFile, folder;
    private Document document;
    private PdfWriter pdfwriter;
    private Paragraph paragraph;
    //DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
    BaseFont marfont;

    private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private Font fSubTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private Font fText = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private Font fHighText = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.RED);

    public static final String FONT = "assets/NotoSansDevanagari-Regular.ttf";
    BaseFont bf_russian = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
    Font russian = new Font(bf_russian, 18);

    public TemplatePDF(Context context, AssetManager asset) throws IOException, DocumentException {
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
            pdfwriter = pdfwriter.getInstance(document, new FileOutputStream(pdfFile));
            MyFooter footerEvent = new MyFooter();
            pdfwriter.setPageEvent(footerEvent);
            document.open();

        } catch (Exception e) {
            Log.e("Open Document", e.toString());
        }
    }

    private void createFile(String filename) {
        File folder = new File(Environment.getExternalStorageDirectory() + "/Download/Calendar/");
        if (!folder.exists())
            folder.mkdir();
        try {
            File file = new File( filename + ".pdf");
            file.createNewFile();

        } catch (IOException e) {
            e.printStackTrace();
        }
        pdfFile = new File(filename + ".pdf");
    }

    public void closeDocument() {
        document.close();
    }

    public void addMetaData(String title, String subject, String author) {
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
    }

    public void addTitles(String title, String subtitle, String date) {
        try {

            paragraph = new Paragraph();
            addChildP(GetItextImage(title, false, 35));
            addChildP(GetItextImage(subtitle, false, 18));
            //addChildP(new Paragraph("Generado:"+date,fHighText));
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("Open Document", e.toString());
        }
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

    private Image GetItextImage1(String text, int wdth, int rowcount) {
        Bitmap bm = textAsBitmap1(text, 18, Color.BLACK, wdth, rowcount);
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

    private void addChildP(Image childParagraph) {
        try {
            childParagraph.setAlignment(Element.ALIGN_CENTER);
            //paragraph.add(childParagraph);
            document.add(childParagraph);
        } catch (Exception e) {
            Log.e("AddParagraph", e.toString());
        }
    }

    private void addChildP1(Image childParagraph) {
        try {
            childParagraph.setAlignment(Element.ALIGN_RIGHT);
            document.add(childParagraph);
        } catch (Exception e) {
            Log.e("AddParagraph", e.toString());
        }
    }

    private void addChildP2(Image childParagraph) {
        try {
            childParagraph.setAlignment(Element.ALIGN_LEFT);
            document.add(childParagraph);
        } catch (Exception e) {
            Log.e("AddParagraph", e.toString());
        }
    }


    public void addParagraph(String text) {
        try {
            //paragraph = new Paragraph(GetItextImage(text));
            Image img = GetItextImage(text, false, 18);
            img.setSpacingAfter(20);
            document.add(img);
        } catch (Exception e) {
            Log.e("AddParagraph", e.toString());
        }
    }

    public void createHeader(String[] header1) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(header1.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;

            while (indexC < header1.length) {
                pdfPCell = new PdfPCell(GetItextImage(header1[indexC++], false, 18));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.GRAY);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPTable.addCell(pdfPCell);
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
        } catch (Exception e) {
            Log.e("createTable", e.toString());
        }
    }

    public void createTable(String[] header, ArrayList<String[]> clients, String[] header1) {
        try {
            paragraph = new Paragraph();
            paragraph.setFont(russian);
            PdfPTable pdfPTable = new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(100);
            float[] columnWidths = {0.18f, 0.15f, 0.15f, 0.21f, 0.21f, 0.24f, 0.15f, 0.41f};
            pdfPTable.setWidths(columnWidths);
            pdfPTable.setSpacingBefore(19);
            PdfPCell pdfPCell;
            int indexC = 0;
            while (indexC < header.length) {
                if (indexC == 5) {
                    pdfPCell = new PdfPCell(GetItextImage1(header[indexC++], 14, 3));
                } else {
                    pdfPCell = new PdfPCell(GetItextImage(header[indexC++], false, 18));
                }
                //pdfPCell = new PdfPCell(GetItextImage(header[indexC++], false , 20));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfPCell.setPadding(5);
                pdfPCell.setColspan(1);
                pdfPTable.addCell(pdfPCell);
            }

            for (int indexR = 0; indexR < clients.size(); indexR++) {
                String[] row = clients.get(indexR);

                for (indexC = 0; indexC < header.length; indexC++) {
                    if (row[indexC] != null && !row[indexC].isEmpty() && !row[indexC].equals("null")) {
                        if (indexC == 3) {
                            pdfPCell = new PdfPCell(GetItextImage1(row[indexC],15,3));
                        }
                        else if (indexC == 4){
                            pdfPCell = new PdfPCell(GetItextImage1(row[indexC],14,3));
                        }
                        else if (indexC == 5) {
                            pdfPCell = new PdfPCell(GetItextImage1(row[indexC], 14, 3));
                        } else {
                            pdfPCell = new PdfPCell(GetItextImage(row[indexC], true, 18));
                        }
                        //pdfPCell = new PdfPCell(GetItextImage(row[indexC],true , 20));
                        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfPCell.setPadding(5);
                        pdfPCell.setFixedHeight(50);
                        pdfPTable.addCell(pdfPCell);
                    } else {
                        pdfPCell = new PdfPCell(new Phrase(row[indexC], russian));
                        pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        pdfPCell.setPadding(5);
                        pdfPCell.setFixedHeight(50);
                        pdfPTable.addCell(pdfPCell);
                    }
                }
            }
            paragraph.add(pdfPTable);
            document.add(paragraph);
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

    public void addSign(String name, String desi) {
        try {

            paragraph = new Paragraph("");
            addChildP1(GetItextImage(name, false, 18));
            addChildP1(GetItextImage(desi, false, 18));
            paragraph.setSpacingBefore(35);
            //addChildP(new Paragraph("Generado:"+date,fHighText));

            document.add(paragraph);
        } catch (Exception e) {
            Log.e("Open Document", e.toString());
        }
    }


    public void addLast(String name) {
        try {

            paragraph = new Paragraph("");
            addChildP2(GetItextImage(name, false, 18));

            document.add(paragraph);
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
        } catch (Exception e) {

        }
    }

    class MyFooter extends PdfPageEventHelper {

        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, footer(), (document.right() - document.rightMargin()) / 1
                    + document.rightMargin(), document.bottom() + 1, 0);

        }

        private Phrase footer() {
            Phrase p = new Phrase("SMART Govt. Calendar App", fText);
            return p;
        }
    }

    /*  public void viewPDF(Context ctx){
          File folder = new File(Environment.getExternalStorageDirectory().toString(),"PDF");
          Intent intent = new Intent(context,ViewPDFActivity.class);
          //File pdfFile1 = new File(folder,"TemplatePDF.pdf");
          intent.putExtra("Path", pdfFile.getPath());
          //Toast.makeText(ctx, (pdfFile.getPath()), Toast.LENGTH_LONG).show();
          intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          context.startActivity(intent);
      }*/
    public void openPDF(MainActivity path) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile() + "TemplatePDF.pdf");
    }

    public Bitmap textAsBitmap(String text, float textSize, int textColor, boolean isSplit) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent(); // ascent() is negative
        int width = (int) (paint.measureText(text) + 0.5f); // round

        if (isSplit) {
            int height = (int) (baseline + paint.descent() + 17.5f);
            Bitmap image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(image);
            try {
                int txtlen1, txtlen2 = 0;
                txtlen1 = text.length();
                if (txtlen1 > 30) {
                    txtlen1 = 30;
                    txtlen2 = text.length() - txtlen1;
                }
                if (txtlen2 > 30)
                    txtlen2 = 30;

                String text1 = text.substring(0, txtlen1);
                canvas.drawText(text1, 0, baseline, paint);
                String text2 = "";
                if (txtlen2 > 0) {
                    text2 = text.substring((txtlen1), (txtlen1 + txtlen2));
                    canvas.drawText(text2, 0, (baseline + 20), paint);
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
            height = (int) (baseline + paint.descent() + 19.5f);
        if (rowcount == 4)
            height = (int) (baseline + paint.descent() + 55.5f);

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
