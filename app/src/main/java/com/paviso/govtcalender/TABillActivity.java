package com.paviso.govtcalender;

import android.app.DownloadManager;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.alamin5g.pdf.PDFView;
//import com.github.barteksc.pdfviewer.PDFView;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.util.ArrayList;

public class TABillActivity extends AppCompatActivity {
    TABillExcel tabillEXCEL;
    PDFView pdfView1;
    private File file;
    //String filename;
    private File pdfFile, excelFile;
    EditText name;
    private String[] header1 = {"गमन", "आगमन", ""};
    private String[] header = {"ठिकाण", "दिंनाक", "वेळ", "ठिकाण", "दिंनाक", "वेळ", "अंतर (कि.मी)", "प्रवास साधन", "प्रवासाचा हेतु"};
    private String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};
    private String[] shorttext = {"नाव :                          पदनाम :                          माहे :                  "};
    private String longText = "कार्यालय : उप-अभियंता, विद्युत उप-विभाग (वि.प्र.) असरजन, मुख्यालय - नांदेड. ";
    private String[] SubTitles = {"प्रवास भत्ता देयक "};
    private String SubTitlesex = "प्रवास भत्ता देयक ";
    private String[] SubHeadTitle = {"    राजप्रत्रित अधिकाऱ्याचे नाव :      " + "                पदनाम :           "};
    private String[] Priceandmonth = {"    मूळ वेतन :       " + "      ग्रेड वेतन :      " + "                                        माहे :        " + "         मुख्यालय :    "};
    private String[] header2 = {"प्रवासाचा आणि मुख्यालयाचा तपशील ", "वाहन भाडे", "मुख्य.अनुपस्थिती"};
    private String[] TAheader = {"ठिकाण", "दिंनाक", "वेळ", "ठिकाण", "दिंनाक", "वेळ", "अंतर (कि.मी)", "प्रवासाचा मार्ग (रेल्वे/बस)",
            "वर्ग ", "रक्कम", "प्रत्यक्ष", "सवलतीचे तास ", "दैनिक भत्यासाठी एकूण दिवस आणि तास "};
    private String[] TBheader = {"दैनिक भत्याचा सर्वसाधारण दर  ", "दैनिक भत्याची रक्कम", "मुक्कामाचा कालावधी   दिवस आणि  तास", "विशेष दर  आणि सर्वसाधारण दर यातील"
            , "विशेष आणि सर्वसाधारण दैनिक दर यातील फरकाची रक्कम", "10,15 व 18 ची बेरीज", "प्रवासाचा उद्देश", "शेरा"};
    private String[] numbers2 = {"14", "15", "16", "17", "18", "19", "20", "21"};
    private String[] SubTitles2 = {"माहे :       "};
    private String[] SubTitles3 = {""};
    private String[] threerdpageheder = {"मागणी केलेली निव्वळ रक्कम"};
    private String[] threerdpageheder2 = {"पहिल्या पानावरुन पुढे घेतलेली रक्कम", ""};
    private String[] threerdpageheder3 = {"रोखीने / धनादेशाने रु."};
    private String[] head = {"रु."};
    private String[] head2 = {"                                                                   रुपये"};
    private String[] threerdpageheder4 = {"प्रमाणित करण्यात येते की,(1)प्रवास भत्ता देकात नमूद केलेली माहिती खरी आहे." +
            "(2)देयकात मागणी केलेली प्रवास भत्ता रक्कम शासन निर्णय,वित्त विभाग, क्रमांक टीआरडब्ल्यु - 2775/451/एडीएम-9 दिनांक.29/11/1975" +
            "आणि क्रमांक टीआरए-1077/156-सी एसईआर-5 दि.11/8/1977 या नुसार व त्या नंतर वेळोवेळी अद्यायावत केलेल्या शासन निर्णयानुसार आहे"};

    private String[] firstpageheder2 = {"मकामुना(एच-४२)-११-२०१२-५,००,०००-पीए३", "सर्वसा.24-म.बाहय(सुधारीत)"};

    private String shorttext1;
    DatabaseHelper mydb;
    private TemplatePDF templatePDF;
    private TABillPDF taBillPDF;

    private String[] karylayachenav = {"कार्यालयाचे नाव :       "};
    private String[] identityNumber = {"ओळखचिन्ह क्रमांक दिनांक :  "};
    private String[] headofAc = {"HEAD OF ACCOUNT"};
    private String[] adminDept = {"Administrative Department  :"};
    private String[] demandNo = {"Demand No                       :"};
    private String[] majorHead = {"Major Head                        :"};
    private String[] subMajorHead = {"Sub Major Head                   :"};
    private String[] subMajorHead2 = {"Sub Minor Head                   :"};


    SharedPreferences sharedpreferences;
    SQLiteDatabase db;
    private String id;
    private String subtitle5, designation, month;
    Cursor cursor;
    private String[] username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabill);
        //  sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        pdfView1 = (PDFView) findViewById(R.id.pdfView1);

        //final String userid = sharedpreferences.getString(LoginActivity.UserId, "");

        mydb = new DatabaseHelper(this);
        db = mydb.getReadableDatabase();

        //  final View mview1 = getLayoutInflater().inflate(R.layout.activity_profile, null);
        // final EditText name = mview1.findViewById(R.id.ed11);


        cursor = db.rawQuery(" SELECT * FROM " + DatabaseHelper.SECOND_TABLE_NAME, username);

        cursor.moveToFirst();
        String name = cursor.getString(1);
        String designation = cursor.getString(2);
        String workplace = cursor.getString(3);
        String mainsalary = cursor.getString(5);
        String mainoffice = cursor.getString(4);
        String month = this.getIntent().getExtras().getString("month");

        shorttext = new String[]{"नाव :" + name + " , " + designation + " , " + "यांचे माहे : " + month + "  चे प्रवास भत्ता देयक "};
        Priceandmonth = new String[]{" मूळ वेतन : " + mainsalary + "   मुख्यालय : " + mainoffice};
        shorttext1 = "" + name + "" + designation;
        subtitle5 = "" + workplace;
        SubHeadTitle = new String[]{" राजपत्रित अधिकाऱ्याचे नाव : " + name + "                           पदनाम : " + designation};
        SubTitles2 = new String[]{" माहे : " + month};

        karylayachenav = new String[]{"कार्यालयाचे नाव :  " + subtitle5};
        headofAc = new String[]{"HEAD OF ACCOUNT"};
        adminDept = new String[]{"Administrative Department  :"};
        demandNo = new String[]{"Demand No                       :"};
        majorHead = new String[]{"Major Head                        :"};
        subMajorHead = new String[]{"Sub Major Head                 :"};
        subMajorHead2 = new String[]{"Sub Minor Head                 :"};

        String startDate = this.getIntent().getExtras().getString("startDate");
        String endDate = this.getIntent().getExtras().getString("endDate");
        String filename = this.getIntent().getExtras().getString("filename");

        try {



            taBillPDF = new TABillPDF(getApplicationContext(), getAssets());
            taBillPDF.openDocument(filename);
            taBillPDF.createfirstTitles(shorttext);
            //taBillPDF.addName(shorttext1);
            taBillPDF.createfirstpageheder2(firstpageheder2);
            taBillPDF.createfirstheader3(subtitle5, month);

            taBillPDF.addMetaData("Clients", "Ventas", "marines");
            //taBillPDF.addTitles("Monthlyश्रीगुरु ", "श्रीगुरु", "");
            taBillPDF.createTitles(shorttext);
            taBillPDF.createSubTitles(SubTitles);
            taBillPDF.createSubHeadTitle(SubHeadTitle);
            taBillPDF.createPriceandmonth(Priceandmonth);
            //taBillPDF.addParagraph(shorttext);
            //taBillPDF.addParagraph(longText);
            taBillPDF.createHeader2(header2);
            taBillPDF.createHeader(header1);
            taBillPDF.createTable(TAheader, TABillDataList2(startDate, endDate), numbers);
            taBillPDF.addTitles();
            taBillPDF.AddSpace();
            taBillPDF.AddSpace();
            taBillPDF.AddSpace();
            taBillPDF.AddSpace();
            taBillPDF.addSign(name, designation);
            //taBillPDF.addMetaData("Clients", "Ventas", "marines");
            //taBillPDF.addTitles("Monthlyश्रीगुरु ", "श्रीगुरु", "");
            //next page tables
            taBillPDF.createTitles2(shorttext);
            taBillPDF.createSubTitles(SubTitles);
            taBillPDF.createSubTitles2(SubTitles2);
            taBillPDF.createSubTitles3(SubTitles3);
            taBillPDF.createSubTitles4(SubTitles3);
            taBillPDF.createSubTitles5(SubTitles3);
            taBillPDF.createTable2(TBheader, TABillDataList3(startDate, endDate), numbers2);
            taBillPDF.AddSpace();
            taBillPDF.AddSpace();
            taBillPDF.AddSpace();
            taBillPDF.AddSpace();
            taBillPDF.AddSpace();
            taBillPDF.AddSpace();
            taBillPDF.AddSpace();
            taBillPDF.AddSpace();
            taBillPDF.AddSpace();
            taBillPDF.AddSpace();
            taBillPDF.addTitles1();

            taBillPDF.createthreerdpageheder(threerdpageheder);
            taBillPDF.createthreerdpageheder2(threerdpageheder2);
            taBillPDF.createthreerdpageheder3(threerdpageheder3);
            taBillPDF.createhead(head);
            taBillPDF.createthreerdpageheder4(threerdpageheder4);
            taBillPDF.creategap(SubTitles3);
            taBillPDF.createthreeheader5(SubTitles3);
            taBillPDF.createhead2(head2);
            taBillPDF.createthreeheader6(SubTitles3);
            taBillPDF.createthreeheader7(SubTitles3);
            taBillPDF.createthreeheader8(SubTitles3);
            taBillPDF.createthreeheader9(SubTitles3);
            taBillPDF.createthreeheader10(SubTitles3);
            taBillPDF.createthreeheader11(SubTitles3);
            taBillPDF.createthreeheader12(SubTitles3);
            taBillPDF.createthreeheader13(SubTitles3);
            taBillPDF.createthreeheader14(SubTitles3);
            //  taBillPDF.createwatermark(SubTitles3);
            taBillPDF.closeDocument();


            tabillEXCEL = new TABillExcel();
            Workbook wb = new HSSFWorkbook();
            //New Sheet
            Sheet sheet = null;
            sheet = wb.createSheet("TABillReport ");
            Cell cHeader = null;
            Cell cHeader1 = null;

            CellStyle Style2 = wb.createCellStyle();
            //Style2.setFillForegroundColor(HSSFColor.LIME.index);
            //Style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            Style2.setAlignment(CellStyle.ALIGN_CENTER);
            Style2.setBorderBottom(CellStyle.BORDER_THICK);
            Style2.setBorderLeft(CellStyle.BORDER_THICK);
            Style2.setBorderRight(CellStyle.BORDER_THICK);
            Style2.setBorderTop(CellStyle.BORDER_THICK);
            Style2.setWrapText(true);


            //Cell style for header row
            CellStyle Style = wb.createCellStyle();
            //Style.setFillForegroundColor(HSSFColor.LIME.index);
            //Style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            Style.setAlignment(CellStyle.ALIGN_CENTER);
            Style.setBorderBottom(CellStyle.BORDER_THICK);
            Style.setBorderLeft(CellStyle.BORDER_THICK);
            Style.setBorderRight(CellStyle.BORDER_THICK);
            Style.setBorderTop(CellStyle.BORDER_THICK);
            Style.setWrapText(true);


            CellStyle Style1 = wb.createCellStyle();
            //Style1.setFillForegroundColor(HSSFColor.LIME.index);
            //Style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            Style1.setAlignment(CellStyle.ALIGN_LEFT);
            Style1.setBorderBottom(CellStyle.BORDER_THICK);
            Style1.setBorderLeft(CellStyle.BORDER_THICK);
            Style1.setBorderRight(CellStyle.BORDER_THICK);
            Style1.setBorderTop(CellStyle.BORDER_THICK);
            Style1.setWrapText(true);

            // Generate column headings
            Row rowHeader1 = sheet.createRow(0);
            tabillEXCEL.createCellRow1(sheet, cHeader1, rowHeader1, 0, Style2, shorttext, name, designation, month);

            Row rowHeader2 = sheet.createRow(1);
            tabillEXCEL.createCellRow2(sheet, cHeader1, rowHeader2, 0, Style, "");

            Row rowHeader5 = sheet.createRow(2);
            tabillEXCEL.createCellRow(sheet, cHeader1, rowHeader5, 0, Style, Style1, "");

            Row rowHeader6 = sheet.createRow(12);
            tabillEXCEL.createCellRow6(sheet, cHeader1, rowHeader6, 1, Style1, "");

            Row rowHeader7 = sheet.createRow(14);
            tabillEXCEL.createCellRow7(sheet, cHeader1, rowHeader7, 0, Style, Style1, "");

            Row rowHeaders1 = sheet.createRow(6);
            tabillEXCEL.createCellRows2(sheet, cHeader1, rowHeaders1, 0, Style1, karylayachenav);
            tabillEXCEL.createCellRows1(sheet, cHeader1, rowHeaders1, 3, Style1, SubTitles2);

            Row rowHeaders3 = sheet.createRow(8);
            tabillEXCEL.createCellRows3(sheet, cHeader1, rowHeaders3, 0, Style, identityNumber);
            tabillEXCEL.createCellRows4(sheet, cHeader1, rowHeaders3, 1, Style, headofAc);

            Row rowHeaders5 = sheet.createRow(9);
            tabillEXCEL.createCellRows5(sheet, cHeader1, rowHeaders5, 1, Style1, adminDept);

            Row rowHeaders6 = sheet.createRow(10);
            tabillEXCEL.createCellRows6(sheet, cHeader1, rowHeaders6, 1, Style1, demandNo);

            Row rowHeaders7 = sheet.createRow(11);
            tabillEXCEL.createCellRows7(sheet, cHeader1, rowHeaders7, 1, Style1, majorHead);

            Row rowHeader8 = sheet.createRow(16);
            tabillEXCEL.createCellRow8(sheet, cHeader1, rowHeader8, 1, Style1, "");

            Row rowHeader9 = sheet.createRow(18);
            tabillEXCEL.createCellRow9(sheet, cHeader1, rowHeader9, 0, Style1, "");

            tabillEXCEL.createTable(sheet, wb, Style, Style1, numbers, TABillDataList2(startDate, endDate),
                    filename, name, designation, TABillDataList3(startDate, endDate), numbers2, TBheader,
                    mainsalary, mainoffice, month, TAheader);
            //progressDialog.hide();
        } catch (Exception e1) {
            Toast.makeText(this, e1.getMessage(), Toast.LENGTH_LONG).show();
        }

        File folder = new File(Environment.getExternalStorageDirectory().toString(), "/Download/Calendar/");
        pdfFile = new File(folder, filename + ".pdf");


        folder = new File(Environment.getExternalStorageDirectory().toString(), "/Download/Calendar/");
        excelFile = new File(folder, (filename + ".xls"));


        try {


            Download(excelFile);


        } catch (Exception ex) {
            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        try {


            pdfView1.fromFile(pdfFile)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .enableAntialiasing(true)
                    .load();

            Download(pdfFile);

        }
        catch (Exception ex) {
            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

   /* public void pdfView1(View view){

        File folder = new File(Environment.getExternalStorageDirectory().toString(),"PDF");
        File pdfFile = new File(folder,filename + ".pdf");
        pdfFile.getPath();
    }*/

    private ArrayList<String[]> TABillDataList2(String startDate, String endDate) {

        DatabaseHelper db = new DatabaseHelper(this);

        return db.TABillDataList2(startDate, endDate);


    }

    private ArrayList<String[]> TABillDataList3(String startDate, String endDate) {

        DatabaseHelper db = new DatabaseHelper(this);
        return db.TABillDataList3(startDate, endDate);
    }

    private void Download(File pdfFile) {
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.addCompletedDownload(pdfFile.getName(), pdfFile.getName(), true, "application/pdf", pdfFile.getAbsolutePath(), pdfFile.length(), true);
    }
}
