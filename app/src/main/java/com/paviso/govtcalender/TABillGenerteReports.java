package com.paviso.govtcalender;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.paviso.Service.Registration;

public class TABillGenerteReports extends AppCompatActivity {

    CardView janView;
    CardView febView;
    CardView marView;
    CardView aprView;
    CardView mayView;
    CardView juneview;
    CardView julyView;
    CardView AugView;
    CardView SeptView;
    CardView OctView;
    CardView NovView;
    CardView DecView;

    DatabaseHelper mydb;
    Registration Remodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabill_generte_reports);

        janView = findViewById(R.id.jan);
        febView = findViewById(R.id.feb);
        marView = findViewById(R.id.mar);
        aprView = findViewById(R.id.Apr);
        mayView = findViewById(R.id.may);
        juneview = findViewById(R.id.june);
        julyView = findViewById(R.id.july);
        AugView = findViewById(R.id.Aug);
        SeptView = findViewById(R.id.Sept);
        OctView = findViewById(R.id.Oct);
        NovView = findViewById(R.id.Nov);
        DecView = findViewById(R.id.Dec);

        mydb = new DatabaseHelper(this);

        Remodel = new Registration();
        Remodel = mydb.GetRegisterData();

        //Jan view
        janView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(TABillGenerteReports.this);
                dialog1.setTitle("Confirm");
                dialog1.setMessage("Do you want to generate report? (Check  Downloads In a File Manager)");
                dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Remodel == null){
                            Toast.makeText(TABillGenerteReports.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent janIntent = new Intent(TABillGenerteReports.this, TABillActivity.class);
                            janIntent.putExtra("startDate", "1/1/2026");
                            janIntent.putExtra("endDate", "31/1/2026");
                            janIntent.putExtra("filename", "TABill_Jan2026");
                            janIntent.putExtra("month", "जानेवारी 2026");
                            startActivity(janIntent);
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

        //Feb view
        febView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(TABillGenerteReports.this);
                dialog1.setTitle("Confirm");
                dialog1.setMessage("Do you want to generate report? (Check  Downloads In a File Manager)");
                dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Remodel == null){
                            Toast.makeText(TABillGenerteReports.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent janIntent = new Intent(TABillGenerteReports.this, TABillActivity.class);
                            janIntent.putExtra("startDate", "1/2/2026");
                            janIntent.putExtra("endDate", "28/2/2026");
                            janIntent.putExtra("filename", "TABill_feb2026");
                            janIntent.putExtra("month", "फेब्रुवारी 2026");
                            startActivity(janIntent);
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

        //Mar View
        marView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(TABillGenerteReports.this);
                dialog1.setTitle("Confirm");
                dialog1.setMessage("Do you want to generate report? (Check  Downloads In a File Manager)");
                dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Remodel == null){
                            Toast.makeText(TABillGenerteReports.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent janIntent = new Intent(TABillGenerteReports.this, TABillActivity.class);
                            janIntent.putExtra("startDate", "1/3/2026");
                            janIntent.putExtra("endDate", "31/3/2026");
                            janIntent.putExtra("filename", "TABill_march2026");
                            janIntent.putExtra("month", "मार्च 2026");
                            startActivity(janIntent);
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

        //April view
        aprView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(TABillGenerteReports.this);
                dialog1.setTitle("Confirm");
                dialog1.setMessage("Do you want to generate report? (Check  Downloads In a File Manager)");
                dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Remodel == null){
                            Toast.makeText(TABillGenerteReports.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent janIntent = new Intent(TABillGenerteReports.this, TABillActivity.class);
                            janIntent.putExtra("startDate", "1/4/2026");
                            janIntent.putExtra("endDate", "30/4/2026");
                            janIntent.putExtra("filename", "TABill_Apr2026");
                            janIntent.putExtra("month", "एप्रिल 2026");
                            startActivity(janIntent);
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

        //May View
        mayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(TABillGenerteReports.this);
                dialog1.setTitle("Confirm");
                dialog1.setMessage("Do you want to generate report? (Check  Downloads In a File Manager)");
                dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Remodel == null){
                            Toast.makeText(TABillGenerteReports.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent janIntent = new Intent(TABillGenerteReports.this, TABillActivity.class);
                            janIntent.putExtra("startDate", "1/5/2026");
                            janIntent.putExtra("endDate", "15/5/2026");
                            janIntent.putExtra("filename", "TABill_May2026");
                            janIntent.putExtra("month", "मे 2026");
                            startActivity(janIntent);
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

        //June View
        juneview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(TABillGenerteReports.this);
                dialog1.setTitle("Confirm");
                dialog1.setMessage("Do you want to generate report? (Check  Downloads In a File Manager)");
                dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Remodel == null){
                            Toast.makeText(TABillGenerteReports.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent janIntent = new Intent(TABillGenerteReports.this, TABillActivity.class);
                            janIntent.putExtra("startDate", "1/6/2026");
                            janIntent.putExtra("endDate", "30/6/2026");
                            janIntent.putExtra("filename", "TABill_June2026");
                            janIntent.putExtra("month", "जून 2026");
                            startActivity(janIntent);
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
        //July View
        julyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(TABillGenerteReports.this);
                dialog1.setTitle("Confirm");
                dialog1.setMessage("Do you want to generate report? (Check  Downloads In a File Manager)");
                dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Remodel == null){
                            Toast.makeText(TABillGenerteReports.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent janIntent = new Intent(TABillGenerteReports.this, TABillActivity.class);
                            janIntent.putExtra("startDate", "1/7/2026");
                            janIntent.putExtra("endDate", "31/7/2026");
                            janIntent.putExtra("filename", "TABill_July2026");
                            janIntent.putExtra("month", "जुलै 2026");
                            startActivity(janIntent);
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
        //Aug View
        AugView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(TABillGenerteReports.this);
                dialog1.setTitle("Confirm");
                dialog1.setMessage("Do you want to generate report? (Check  Downloads In a File Manager)");
                dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Remodel == null){
                            Toast.makeText(TABillGenerteReports.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent janIntent = new Intent(TABillGenerteReports.this, TABillActivity.class);
                            janIntent.putExtra("startDate", "1/8/2026");
                            janIntent.putExtra("endDate", "31/8/2026");
                            janIntent.putExtra("filename", "TABill_Aug2026");
                            janIntent.putExtra("month", "ऑगस्ट 2026");
                            startActivity(janIntent);
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
        //Sept View
        SeptView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(TABillGenerteReports.this);
                dialog1.setTitle("Confirm");
                dialog1.setMessage("Do you want to generate report? (Check  Downloads In a File Manager)");
                dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Remodel == null){
                            Toast.makeText(TABillGenerteReports.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent janIntent = new Intent(TABillGenerteReports.this, TABillActivity.class);
                            janIntent.putExtra("startDate", "1/9/2026");
                            janIntent.putExtra("endDate", "30/9/2026");
                            janIntent.putExtra("filename", "TABill_Sept2026");
                            janIntent.putExtra("month", "सप्टेंबर 2026");
                            startActivity(janIntent);
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

        //Oct View
        OctView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(TABillGenerteReports.this);
                dialog1.setTitle("Confirm");
                dialog1.setMessage("Do you want to generate report? (Check  Downloads In a File Manager)");
                dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Remodel == null){
                            Toast.makeText(TABillGenerteReports.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent janIntent = new Intent(TABillGenerteReports.this, TABillActivity.class);
                            janIntent.putExtra("startDate", "1/10/2026");
                            janIntent.putExtra("endDate", "31/10/2026");
                            janIntent.putExtra("filename", "TABill_Oct2026");
                            janIntent.putExtra("month", "ऑक्टोबर 2026");
                            startActivity(janIntent);
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

        //Nov view
        NovView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(TABillGenerteReports.this);
                dialog1.setTitle("Confirm");
                dialog1.setMessage("Do you want to generate report? (Check  Downloads In a File Manager)");
                dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Remodel == null){
                            Toast.makeText(TABillGenerteReports.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent janIntent = new Intent(TABillGenerteReports.this, TABillActivity.class);
                            janIntent.putExtra("startDate", "1/11/2026");
                            janIntent.putExtra("endDate", "30/11/2026");
                            janIntent.putExtra("filename", "TABill_Nov2026");
                            janIntent.putExtra("month", "नोव्हेंबर 2026");
                            startActivity(janIntent);
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

        //Dec View
        DecView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder dialog1 = new AlertDialog.Builder(TABillGenerteReports.this);
                dialog1.setTitle("Confirm");
                dialog1.setMessage("Do you want to generate report? (Check  Downloads In a File Manager)");
                dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (Remodel == null){
                            Toast.makeText(TABillGenerteReports.this, "Please Fill My Profile First", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent janIntent = new Intent(TABillGenerteReports.this, TABillActivity.class);
                            janIntent.putExtra("startDate", "1/12/2026");
                            janIntent.putExtra("endDate", "31/12/2026");
                            janIntent.putExtra("filename", "TABill_Dec2026");
                            janIntent.putExtra("month", "डिसेंबर 2026");
                            startActivity(janIntent);
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
    }
}
