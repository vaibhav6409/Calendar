package com.paviso.govtcalenders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class TitleSponsorActivity extends AppCompatActivity {
    ImageView SGU_Id, XDTPL_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_sponsers);

        SGU_Id = (ImageView) findViewById(R.id.SGU_Id);
        XDTPL_Id = (ImageView) findViewById(R.id.XDTPL_Id);

        SGU_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://www.sanjayghodawatuniversity.ac.in/");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }
        });

        XDTPL_Id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://xdtpl.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);

            }
        });

        }
}
