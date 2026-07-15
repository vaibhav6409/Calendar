package com.xdtpl.smartgovtcalender;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xdtpl.Service.Registration;

public class RegistrationActivity extends AppCompatActivity {
 DatabaseHelper mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mydb = new DatabaseHelper(this);
        final EditText Name = findViewById(R.id.name);
        final EditText WorkPlace = findViewById(R.id.work);
        final EditText Designation = findViewById(R.id.desig);
        final EditText Email = findViewById(R.id.mail);
        final EditText Password = findViewById(R.id.pass);
        final EditText ConfirmPassword = findViewById(R.id.conpass);
        final TextView login = findViewById(R.id.log);

        Button register = (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    boolean isError = false;
                    if (Name.length() == 0) {
                        Name.requestFocus();
                        Name.setError("Field cannot be empty");
                        isError = true;
                    }

                   if (WorkPlace.length() == 0) {
                        WorkPlace.requestFocus();
                        WorkPlace.setError("Field cannot be empty");
                        isError = true;
                    }

                   if (Designation.length() == 0) {
                        Designation.requestFocus();
                        Designation.setError("Field cannot be empty");
                        isError = true;
                    }

                   if (Email.length() == 0) {
                        Email.requestFocus();
                        Email.setError("Field cannot be empty");
                        isError = true;
                    }
                   if (Password.length() == 0) {
                    Password.requestFocus();
                    Password.setError("Field cannot be empty");
                    isError = true;
                }
                   if (ConfirmPassword.length() == 0) {
                        ConfirmPassword.requestFocus();
                        ConfirmPassword.setError("Field cannot be empty");
                        isError = true;
                    }
                    if (isError)
                        return;


                    if(Password.getText().toString().equals(ConfirmPassword.getText().toString()))
                    {
                        Registration model = new Registration();
                        model.UserName = Name.getText().toString();
                        model.WorkName = WorkPlace.getText().toString();
                        model.Designation = Designation.getText().toString();
                        model.MainOffice = Email.getText().toString();
                        model.MainSalary = Password.getText().toString();


                        boolean isInserted =   mydb.SecondinsertData(model);

                        try {
                            if (isInserted)
                                Toast.makeText(getBaseContext(), " यशस्वीरित्या रेजिस्ट्रेशन झाले ", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getBaseContext(), " त्रुटी", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
              else{
                        Toast.makeText(getBaseContext(),"पासवर्ड आणि कन्फर्म पासवर्ड समान असणे आवश्यक आहे",Toast.LENGTH_LONG).show();
                    }


            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegistrationActivity.this , LoginActivity.class);
                 startActivity(intent);
            }
        });
    }
}
