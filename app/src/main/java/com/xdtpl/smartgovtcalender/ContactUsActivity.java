package com.xdtpl.smartgovtcalender;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        final EditText editTextName = findViewById(R.id.name);
        final EditText editTextSubject = findViewById(R.id.email);
        final EditText editTextMessage = findViewById(R.id.message);
        final EditText editTextMobileNo = findViewById(R.id.mobileno);

        Button startBtn = findViewById(R.id.sendmail);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isError = false;

                if (editTextName.length() == 0) {
                    editTextName.setError("Field cannot be empty");
                    editTextName.requestFocus();
                    isError = true;
                }

                if (editTextSubject.length() == 0) {
                    editTextSubject.setError("Field cannot be empty");
                    editTextSubject.requestFocus();
                    isError = true;
                }

                if (editTextMessage.length() == 0) {
                    editTextMessage.setError("Field cannot be empty");
                    editTextMessage.requestFocus();
                    isError = true;
                }

                if (editTextMobileNo.length() == 0) {
                    editTextMobileNo.setError("Field cannot be empty");
                    editTextMobileNo.requestFocus();
                    isError = true;
                }

                // Stop if any field is empty
                if (isError) {
                    return;
                }

                String subject = editTextSubject.getText().toString().trim();
                String message = editTextMessage.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String mobileNo = editTextMobileNo.getText().toString().trim();

                String[] to = {
                        "smartinnovations9009@gmail.com"
                };

                Intent email = new Intent(Intent.ACTION_SEND);
                email.setType("message/rfc822");

                email.putExtra(Intent.EXTRA_EMAIL, to);
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(
                        Intent.EXTRA_TEXT,
                        message
                                + "\n\n"
                                + "Name: " + name
                                + "\n"
                                + "Mobile No: " + mobileNo
                );

                try {
                    startActivity(
                            Intent.createChooser(
                                    email,
                                    "Choose an Email client:"
                            )
                    );

                    editTextName.getText().clear();
                    editTextSubject.getText().clear();
                    editTextMessage.getText().clear();
                    editTextMobileNo.getText().clear();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}