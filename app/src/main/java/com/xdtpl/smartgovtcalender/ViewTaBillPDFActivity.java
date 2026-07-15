package com.xdtpl.smartgovtcalender;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.alamin5g.pdf.PDFView;
//import com.github.barteksc.pdfviewer.PDFView;

public class ViewTaBillPDFActivity extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ta_bill_pdf);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset("2. TA Bill Sample Report 2Ajinkya Patil - 22-Jan-2019 - 8-44 PM.pdf")
                .load();
    }
}
