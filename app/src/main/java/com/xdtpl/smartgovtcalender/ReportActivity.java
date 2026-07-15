package com.xdtpl.smartgovtcalender;

import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alamin5g.pdf.PDFView;
//import com.github.barteksc.pdfviewer.PDFView;
import com.xdtpl.Service.Registration;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReportActivity extends AppCompatActivity {
    Context context;
    CheckBox pdf,excel;
    CharSequence[] ACTIVITY_SELECTION_EXPORT = {"PDF", "EXPORT"};
    Calendar cal;
    int d,m,y;
    String first ,second;
    private File pdfFile, excelFile;
    EditText date1,date2;
    String startdate,enddate;
    PDFView pdfView;
    DatabaseHelper db;
    Registration Remodel;

    TemplateEXCEL templateEXCEL;
    EditText EditStartDate, EditEndDate;
    DatePickerDialog picker, picker1;
    int daystart, monthstart, yearstart;
    int dayend, monthend, yearend;

    String userId, startDate, endDate;

    private String[] header1 = {"गमन", "आगमन", ""};
    private String[] header = {"दिंनाक", "वेळ पासून ", "वेळ पर्यंत", "ठिकाण पासून", "ठिकाण पर्यंत", "वाहनाचा प्रकार", "अंतर(कि.मी)", "कामाचा तपशील"};
    private String shorttext = "नाव :                  पदनाम :                               ";
    private String shorttext1 = "नाव:";
    private String shorttext2 = "पदनाम:";
    private String shorttext3 = "मुख्यालय:";
    private String longText = "";
    private String[] TAheader = {"From Date", "From Time", "Place", "To Date", "To Time", "Distance(km)", "Mode of travel", "Class", "Price", "Pratakshya", "Savalatiche Tas"};
    private TemplatePDF templatePDF;
    //private TABillPDF taBillPDF;
    Cursor cursor;
    SQLiteDatabase db1;
    Button list;
   // private String[]shortText;
    private String[]username;
    private String name2,desi;
    DownloadManager downloadManager;

    ProgressBar loadingProgress;
    TextView loaderText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_report);

            context = this;

            loadingProgress = findViewById(R.id.loadingProgress);
            loaderText = (TextView) findViewById(R.id.loaderText);

            pdfView = (PDFView) findViewById(R.id.pdfView);
            db = new DatabaseHelper(this);
            Remodel = new Registration();
            Remodel = db.GetRegisterData();

           db1 = db.getReadableDatabase();
           cursor = db1.rawQuery(" SELECT * FROM " + DatabaseHelper.SECOND_TABLE_NAME,username);
           if(cursor.getCount()>0) {
               cursor.moveToFirst();
               String name = cursor.getString(1);
               String designation = cursor.getString(2);
               String workplace = cursor.getString(3);
               String mainoffice = cursor.getString(4);
               shorttext = " नाव : " + name+"                         "  + "  पदनाम : "+designation +"              "+ " मुख्यालय : " + mainoffice;
               longText="प्रति : "+ workplace + "  यांना  मंजुरी  करीत सविनय सादर ";
               name2 = ""+name;
               desi = ""+designation;

           }

            date1 = findViewById(R.id.ed);
            date1.setInputType(InputType.TYPE_NULL);
            date1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    picker = new DatePickerDialog(ReportActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                    ReportActivity.this.yearstart = year;
                                    ReportActivity.this.monthstart = monthOfYear;
                                    ReportActivity.this.daystart = dayOfMonth;

                                    date1.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                    startdate = date1.getText().toString();

                                }
                            }, yearstart, monthstart, daystart);
                    picker.show();
                }
            });


            date2 = findViewById(R.id.enddate);
            date2.setInputType(InputType.TYPE_NULL);
            date2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    picker1 = new DatePickerDialog(ReportActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                                    ReportActivity.this.yearend = year;
                                    ReportActivity.this.monthend = monthOfYear;
                                    ReportActivity.this.dayend = dayOfMonth;

                                    date2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                    enddate = date2.getText().toString();
                                }
                            }, yearend, monthend, dayend);
                    picker1.show();
                }
            });

           Calendar cldr = Calendar.getInstance();

            ReportActivity.this.yearend = cldr.get(android.icu.util.Calendar.YEAR);
            ReportActivity.this.monthend = cldr.get(android.icu.util.Calendar.MONTH);
            ReportActivity.this.dayend = cldr.get(android.icu.util.Calendar.DAY_OF_MONTH);

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String dateOutput = format.format(cldr.getTime());
            date2.setText(dateOutput);

            cldr.add(android.icu.util.Calendar.MONTH, -1);

            ReportActivity.this.yearstart = cldr.get(android.icu.util.Calendar.YEAR);
            ReportActivity.this.monthstart = cldr.get(android.icu.util.Calendar.MONTH);
            ReportActivity.this.daystart = cldr.get(android.icu.util.Calendar.DAY_OF_MONTH);

            dateOutput = format.format(cldr.getTime());
            date1.setText(dateOutput);

            final Button btn = findViewById(R.id.btn);
            final DatabaseHelper mydb = new DatabaseHelper(this);

            pdf=(CheckBox)findViewById(R.id.pdf);
            excel=(CheckBox)findViewById(R.id.excel);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final AlertDialog.Builder dialog1 = new AlertDialog.Builder(ReportActivity.this);
                    dialog1.setTitle("Confirm");
                    dialog1.setMessage("Do you want to generate report?  (Check  Downloads In a File Manager)");
                    dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            if (date1.length() == 0 && date2.length() == 0 || (!pdf.isChecked() && !excel.isChecked())) {
                                boolean isError = false;
                                if (date1.length() == 0) {
                                    date1.requestFocus();
                                    date1.setError("Field cannot be empty");
                                    isError = true;
                                }

                                if (date2.length() == 0) {
                                    date2.requestFocus();
                                    date2.setError("Field cannot be empty");
                                    isError = true;
                                }
                                if(!pdf.isChecked() || !excel.isChecked()) {
                                    //pdf.setChecked(!pdf.isChecked());
                                    pdf.setError("*");
                                    excel.setError("*");
                                    Toast.makeText(context,"Please check atleast one checkbox",Toast.LENGTH_LONG);
                                    isError = true;
                                }

                                if (isError)
                                    return;
                            } else if(Remodel == null){
                                Toast.makeText(ReportActivity.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                            }else {

                                pdf.setError(null);
                                excel.setError(null);
                                if(pdf.isChecked()){

                                    //loaderText.setVisibility(View.VISIBLE);
                                    pdfExport();

                                }
                                if(excel.isChecked()){

                                   // loaderText.setVisibility(View.VISIBLE);
                                    excelExport();

                                }

                                /*if(pdf.isChecked() && excel.isChecked()){
                                    pdfExport();
                                    excelExport();
                                }*/
                                //showSelectorDialogExport();
                            }

                        }
                    });
                    dialog1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog1.show();

                }

            });


        }catch (Exception ex)
        {
            Toast.makeText(getBaseContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
        }

}


    public void pdfExport() {

        startdate = date1.getText().toString();
        enddate = date2.getText().toString();

        DateFormat df, df1;
        String fileName = "TourDiary.pdf";

        df = new SimpleDateFormat("dd/MM/yyyy");
        df1 = new SimpleDateFormat("dd-MM-yyyy");

        try {
            Date startDate1 = df.parse(startdate);
            Date endDate1 = df.parse(enddate);

            fileName = "TourDiary "
                    + df1.format(startDate1)
                    + " to "
                    + df1.format(endDate1);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //ReportDataList(startdate,enddate);
        //   Intent intent = new Intent(ReportActivity.this,ViewPDFActivity.class);
        //  startActivity(intent);
        try {
            templatePDF = new TemplatePDF(getApplicationContext(), getAssets());
            templatePDF.openDocument(Environment.getExternalStorageDirectory().toString() +"/Download/Calendar/"+ fileName);
            templatePDF.addMetaData("Clients", "Ventas", "marines");
            templatePDF.addTitles("मासिक दैनंदिनी", "", "");
            templatePDF.addParagraph(shorttext);
            //templatePDF.createHeader(header1);
            templatePDF.createTable(header, ReportDataList(startdate, enddate), header1);
            //templatePDF.createwatermark(SubTitles3);
            templatePDF.AddSpace();
            templatePDF.AddSpace();
            templatePDF.AddSpace();
            templatePDF.AddSpace();
            templatePDF.AddSpace();
            templatePDF.AddSpace();
            templatePDF.addSign(name2, desi);
            templatePDF.addLast(longText);
            templatePDF.closeDocument();
//            Path src =  Paths.get(fileName);
//            System.out.println(src);
//            Path target= Paths.get(Environment.getExternalStorageDirectory().toString()+"/Download/Calendar/");
//            System.out.println(target);
//            Files.move(src,target);
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        File folder = new File(Environment.getExternalStorageDirectory().toString(), "/Download/Calendar/");
        pdfFile = new File(folder, (fileName + ".pdf"));


        try {
            Download(pdfFile);

        } catch (Exception ex) {
            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void excelExport() {

        startdate = date1.getText().toString();
        enddate = date2.getText().toString();
        DateFormat df, df1;
        String fileName = "TourDiary.xls";
        df = new SimpleDateFormat("dd/MM/yyyy");
        df1 = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate1 = null;
        Date endDate1 = null;
        try {
            startDate1 = df.parse(startdate);
            endDate1 = df.parse(enddate);
            fileName = "TourDiary " + df1.format(startDate1) + " to " + df1.format(endDate1);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            templateEXCEL = new TemplateEXCEL();
            Workbook wb = new HSSFWorkbook();
            //New Sheet
            Sheet sheet = null;
            sheet = wb.createSheet("TourDiary " + df1.format(startDate1) + " to " + df1.format(endDate1));
            Cell cHeader = null;
            Cell cHeader1 = null;
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

            // Generate column headings
            Row rowHeader = sheet.createRow(0);
            templateEXCEL.createCellRow(sheet, cHeader, rowHeader, 0, Style, "मासिक दैनंदिनी");
            Row rowHeader1 = sheet.createRow(1);
            templateEXCEL.createCellRow1(sheet, cHeader1, rowHeader1, 0, Style, shorttext);
            //sheet = null;
            templateEXCEL.createTable(sheet, wb, Style, header, ReportDataList(startdate, enddate), fileName);

            templateEXCEL.createsheet(sheet, wb, Style, header, ReportDataList(startdate, enddate), fileName);

            /*templateEXCEL.addSign(name2, desi);
            templateEXCEL.addLast(longText);*/
            //templateEXCEL.closeDocument();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        File folder = new File(Environment.getExternalStorageDirectory().toString(), "/Download/Calendar/");
        excelFile = new File(folder, (fileName + ".xls"));


        try {
            Download(excelFile);

        } catch (Exception ex) {
            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void pdfView(View view){
        //   templatePDF.viewPDF(this);
        File folder = new File(Environment.getExternalStorageDirectory().toString(),"PDF");
        // Intent intent = new Intent(context,ViewPDFActivity.class);
        File pdfFile = new File(folder,"TemplatePDF.pdf");
        pdfFile.getPath();
        //  intent.putExtra("Path", pdfFile.getPath());
        //Toast.makeText(ctx, (pdfFile.getPath()), Toast.LENGTH_LONG).show();
        // intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        // context.startActivity(intent);
    }

    private ArrayList<String[]> ReportDataList(String startdate ,String enddate){
        DatabaseHelper db = new DatabaseHelper(this);
        return db.ReportDataList(startdate,enddate);
    }


    private void Download (File pdfFile){
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadManager.addCompletedDownload(pdfFile.getName(), pdfFile.getName(), true, "application/pdf", pdfFile.getAbsolutePath(), pdfFile.length(), true);
    }

    private void ShowToast(String msg, Context ctx){
        Toast.makeText(ctx,msg,Toast.LENGTH_LONG).show();
    }

    }

