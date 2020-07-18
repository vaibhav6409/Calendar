package com.xdtpl.smartgovtcalender;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    CardView janView;
    CardView febView;
    CardView marView;
    CardView aprView;
    CardView mayView;
    CardView juneView;
    CardView julyView;
    CardView AugView;
    CardView septView;
    CardView octView;
    CardView novView;
    CardView decView;

    Button diary,bill,ref;
    ImageView ad1,ad2;

    private static final int YOUR_PERMISSION_STATIC_CODE_IDENTIFIER = 100;
    private String[]header1={"Gaman","Aagaman"};
    private String[]header={"ठिकाण","दिंनाक","वेळ","ठिकाण","दिंनाक","वेळ","अंतर (कि.मी)","प्रवास साधन","प्रवासाचा हेतु"};
    private String shorttext="नाव : श्री. राजेंद्र नानासाहेब शेळके   पदनाम : सहायक अभियंता श्रेणी-2    माहे : फेब्रुवारी - 2017";
    private String longText="कार्यालय : उप-अभियंता, विद्युत उप-विभाग (वि.प्र.) असरजन, मुख्यालय - नांदेड. ";
    private String[]TAheader={"From Date","From Time","Place","To Date","To Time","Distance(km)","Mode of travel","Class","Price","Pratakshya","Savalatiche Tas"};
    private TemplatePDF templatePDF;
    //private TABillPDF taBillPDF;
    Button list;
    DownloadManager downloadManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        janView = findViewById(R.id.jan);
        febView = findViewById(R.id.feb);
        marView = findViewById(R.id.mar);
        aprView = findViewById(R.id.Apr);
        mayView = findViewById(R.id.may);
        juneView = findViewById(R.id.june);
        julyView = findViewById(R.id.july);
        AugView = findViewById(R.id.Aug);
        septView = findViewById(R.id.Sept);
        octView = findViewById(R.id.Oct);
        novView = findViewById(R.id.Nov);
        decView = findViewById(R.id.Dec);

        diary = findViewById(R.id.diary);
        bill = findViewById(R.id.bill);
        ad1 = findViewById(R.id.ad1);
        ad2 = findViewById(R.id.ad2);
        //ref=findViewById(R.id.ref);

        janView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent janIntent = new Intent(HomeActivity.this, MainActivity.class);
                janIntent.putExtra("value", 0);
                startActivity(janIntent);
            }
        });

        febView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent janIntent = new Intent(HomeActivity.this, MainActivity.class);
                janIntent.putExtra("value", 1);
                startActivity(janIntent);
            }
        });

        marView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent janIntent = new Intent(HomeActivity.this, MainActivity.class);
                janIntent.putExtra("value", 2);
                startActivity(janIntent);
            }
        });

        aprView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent janIntent = new Intent(HomeActivity.this, MainActivity.class);
                janIntent.putExtra("value", 3);
                startActivity(janIntent);
            }
        });
        mayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent janIntent = new Intent(HomeActivity.this, MainActivity.class);
                janIntent.putExtra("value", 4);
                startActivity(janIntent);
            }
        });

        /*juneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent janIntent = new Intent(HomeActivity.this, MainActivity.class);
                janIntent.putExtra("value", 5);
                startActivity(janIntent);
            }
        });*/

        /*julyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent janIntent = new Intent(HomeActivity.this, MainActivity.class);
                janIntent.putExtra("value", 6);
                startActivity(janIntent);
            }
        });
*/
        /*AugView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent janIntent = new Intent(HomeActivity.this, MainActivity.class);
                janIntent.putExtra("value", 7);
                startActivity(janIntent);
            }
        });*/

       /* septView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent janIntent = new Intent(HomeActivity.this, MainActivity.class);
                janIntent.putExtra("value", 8);
                startActivity(janIntent);
            }
        });*/

       /* octView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent janIntent = new Intent(HomeActivity.this, MainActivity.class);
                janIntent.putExtra("value", 9);
                startActivity(janIntent);
            }
        });
*/
        /*novView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent janIntent = new Intent(HomeActivity.this, MainActivity.class);
                janIntent.putExtra("value", 10);
                startActivity(janIntent);
            }
        });*/

        /*decView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent janIntent = new Intent(HomeActivity.this, MainActivity.class);
                janIntent.putExtra("value", 11);
                startActivity(janIntent);
            }
        });*/

        diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });

        bill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TABillGenerteReports.class);
                startActivity(intent);
            }
        });

    /*    ref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(HomeActivity.this);
                dialog1.setTitle("Please Contact Us");
                dialog1.setMessage("Email Id : smartinnovations9009@gmail.com");
                dialog1.show();
            }
        }); */

        ad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.sanjayghodawatuniversity.ac.in/");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }
        });

        ad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://xdtpl.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }
        });

        //Pdf file code

    /*    ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                YOUR_PERMISSION_STATIC_CODE_IDENTIFIER);

        try {
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
        }*/
    }

   /* public void pdfView(View view){
      //  templatePDF.viewPDF(this);
    }

    private ArrayList<String[]> GetDataList(){
        DatabaseHelper db = new DatabaseHelper(this);
        //db.insertData("kolhapur","11:20","Pune","3:00","Bus","","","31/12/2018","20km","Electricity");
        //db.insertData("Nashik","11:00am","Sangli","6:00pm","Train","","","21/12/2018","20km","Weekly Holiday");

        return db.GetDataList();
    }*/

}

