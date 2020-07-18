package com.xdtpl.smartgovtcalender;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        final EditText editTextTo1=(EditText)findViewById(R.id.name);
        final EditText editTextSubject=(EditText)findViewById(R.id.email);
        final EditText editTextMessage=(EditText)findViewById(R.id.message);

        Button startBtn = (Button) findViewById(R.id.sendmail);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTo1.length() == 0 && editTextSubject.length() == 0 && editTextMessage.length() == 0) {
                    boolean isError = false;
                    if (editTextTo1.length() == 0) {
                        editTextTo1.requestFocus();
                        editTextTo1.setError("Field cannot be empty");
                        isError = true;
                    }

                    if (editTextSubject.length() == 0) {
                        editTextSubject.requestFocus();
                        editTextSubject.setError("Field cannot be empty");
                        isError = true;
                    }

                    if (editTextMessage.length() == 0) {
                        editTextMessage.requestFocus();
                        editTextMessage.setError("Field cannot be empty");
                        isError = true;
                    }
                }else {

                String subject=editTextSubject.getText().toString();
                String message=editTextMessage.getText().toString();
                String name = editTextTo1.getText().toString();

                String[] to = {
                        "smartinnovations9009@gmail.com"
                };
                Intent email = new Intent(Intent.ACTION_SEND);
                email.setData(Uri.parse("mailto:"));
                email.setType("text/plain");
                email.putExtra(Intent.EXTRA_EMAIL, to);
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message+"\n"+"\n"+"---"+name);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));

                    editTextTo1.getText().clear();
                    editTextSubject.getText().clear();
                    editTextMessage.getText().clear();
            }}
        });
        }
}
