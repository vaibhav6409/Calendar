package com.xdtpl.smartgovtcalender;

import android.app.DownloadManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class ViewPDFActivity extends AppCompatActivity {
    PDFView pdfView;
    private File file;
    private File pdfFile;
    private ContextCompat context;

    // Calendar calendar;
    EditText EditStartDate;


    private String[] header1 = {"Gaman", "Aagaman"};
    private String[] header = {"ठिकाण", "दिंनाक", "वेळ", "ठिकाण", "दिंनाक", "वेळ", "अंतर (कि.मी)", "प्रवास साधन", "प्रवासाचा हेतु"};
    private String shorttext = "नाव : श्री. राजेंद्र नानासाहेब शेळके   पदनाम : सहायक अभियंता श्रेणी-2    माहे : फेब्रुवारी - 2017";
    private String longText = "कार्यालय : उप-अभियंता, विद्युत उप-विभाग (वि.प्र.) असरजन, मुख्यालय - नांदेड. ";
    private String[] TAheader = {"From Date", "From Time", "Place", "To Date", "To Time", "Distance(km)", "Mode of travel", "Class", "Price", "Pratakshya", "Savalatiche Tas"};
    private TemplatePDF templatePDF;
    Button list;
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);

        pdfView = (PDFView) findViewById(R.id.pdfView);

        pdfView.fromAsset("1. Tour Diary Sample 1 Ajinkya  - 22-Jan-2019 - 9-00 PM.pdf")
                .load();




     /*   try {
            templatePDF = new TemplatePDF(getApplicationContext(), getAssets());
            templatePDF.openDocument();
            templatePDF.addMetaData("Clients", "Ventas", "marines");
            templatePDF.addTitles("मासिक दैनंदिनी", "", "");
            templatePDF.addParagraph(shorttext);
            templatePDF.addParagraph(longText);
            templatePDF.createTable(header, GetDataList(),header1);
            templatePDF.closeDocument();
        }
        catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(),Toast.LENGTH_LONG).show();
        }

              File folder = new File(Environment.getExternalStorageDirectory().toString(),"/Download/Calendar/");
        pdfFile = new File(folder,"TemplatePDF.pdf");

        pdfView.fromFile(pdfFile)
                .enableSwipe(true)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .enableAntialiasing(true)
                .load();

 }

    public void pdfView(View view){

        File folder = new File(Environment.getExternalStorageDirectory().toString(),"PDF");
        File pdfFile = new File(folder,"TemplatePDF.pdf");
        pdfFile.getPath();
    }

    private ArrayList<String[]> GetDataList(){
        DatabaseHelper db = new DatabaseHelper(this);
        //db.insertData("kolhapur","11:20","Pune","3:00","Bus","","","31/12/2018","20km","Electricity");
        //db.insertData("Nashik","11:00am","Sangli","6:00pm","Train","","","21/12/2018","20km","Weekly Holiday");

        return db.GetDataList();
    }*/

    }
}

