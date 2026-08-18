package com.paviso.govtcalender;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Medicalbill extends AppCompatActivity {

    int view = R.layout.activity_medicalbill;
    Button button;
    EditText emailId,name,mobile,dist,pin;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicalbill);
        button = findViewById(R.id.sendmail);
        emailId = findViewById(R.id.email);
        name = findViewById(R.id.name);
        mobile = findViewById(R.id.mobile);
        dist = findViewById(R.id.dist);
        pin = findViewById(R.id.pin);

        button.setOnClickListener(new View.OnClickListener() {
            boolean isError = false;
            @Override
            public void onClick(View v) {
                if (emailId.getText().toString().isEmpty()) {
                    name.requestFocus();
                    emailId.setError("enter email address");
                    isError = true;
                } else {
                    if (!emailId.getText().toString().trim().matches(emailPattern)) {
                        name.requestFocus();
                        emailId.setError("Invalid email address");
                        isError = true;
                    }
                }
                if (name.length() == 0) {
                    name.requestFocus();
                    name.setError("Please enter your name");
                    isError = true;
                }

                if (mobile.length() == 0) {
                    mobile.requestFocus();
                    mobile.setError("Please enter your mobile number");
                    isError = true;
                }

                if (dist.length() == 0) {
                    dist.requestFocus();
                    dist.setError("Please enter your district");
                    isError = true;
                }

                if (pin.length() == 0) {
                    pin.requestFocus();
                    pin.setError("Please enter your area pincode");
                    isError = true;
                }

                if (isError)
                    return;

                try {
                    final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                    emailIntent.setType("text/html");
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ "smartmedibills@gmail.com"});
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Medical Bill");
                    emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml(new StringBuilder()
                            .append("<p><b> Name : "+ name.getText().toString() + "</b></p>")
                            .append("<p><b> Mobile Number : "+ mobile.getText().toString() + "</b></p>")
                            .append("<p><b> Email : "+ emailId.getText().toString() + "</b></p>")
                            .append("<p><b> Dist : "+ dist.getText().toString() + "</b></p>")
                            .append("<p><b> Pin code : "+ pin.getText().toString() + "</b></p>")
                            .toString()));
                    startActivity(Intent.createChooser(emailIntent, "Email:"));

                }
                catch (Exception ex) {

                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}