package com.xdtpl.smartgovtcalender;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.xdtpl.Service.Registration;

public class ProfileActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        mydb = new DatabaseHelper(this);

        final EditText Id = findViewById(R.id.edd);
        final EditText name = findViewById(R.id.ed11);
        final EditText designation = findViewById(R.id.ed22);
        final EditText Workplace = findViewById(R.id.ed3);
        final EditText mainOffice = findViewById(R.id.ed4);
        final EditText MainSalary = findViewById(R.id.ed5);
        //  Button save = findViewById(R.id.save);
        edit = findViewById(R.id.edit);

        Registration Remodel = mydb.GetRegisterData();
        if (Remodel != null) {
            Id.setText(Remodel.Id);
            name.setText(Remodel.UserName);
            designation.setText(Remodel.Designation);
            Workplace.setText(Remodel.WorkName);
            mainOffice.setText(Remodel.MainOffice);
            MainSalary.setText(Remodel.MainSalary);
        }




        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isError = false;
                if (name.length() == 0) {
                    name.requestFocus();
                    name.setError("Field cannot be empty");
                    isError = true;
                }

                if (designation.length() == 0) {
                    designation.requestFocus();
                    designation.setError("Field cannot be empty");
                    isError = true;
                }

                if (Workplace.length() == 0) {
                    Workplace.requestFocus();
                    Workplace.setError("Field cannot be empty");
                    isError = true;
                }

                if (mainOffice.length() == 0) {
                    mainOffice.requestFocus();
                    mainOffice.setError("Field cannot be empty");
                    isError = true;
                }
                if (MainSalary.length() == 0) {
                    MainSalary.requestFocus();
                    MainSalary.setError("Field cannot be empty");
                    isError = true;
                }

                if (isError)
                    return;

                try {
                    Registration Remodel = new Registration();
                    Remodel = mydb.GetRegisterData();

                    if (Remodel == null) {
                        Registration model1 = new Registration();
                        model1.UserName = name.getText().toString();
                        model1.Designation = designation.getText().toString();
                        model1.WorkName = Workplace.getText().toString();
                        model1.MainOffice = mainOffice.getText().toString();
                        model1.MainSalary = MainSalary.getText().toString();


                        boolean isInserted = mydb.SecondinsertData(model1);

                        try {
                            if (isInserted) {
                                Toast.makeText(getBaseContext(), " यशस्वीरित्या सेव झाले ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else
                                Toast.makeText(getBaseContext(), " त्रुटी", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }


                    } else {


                        Registration model = new Registration();
                        model.Id = Id.getText().toString();
                        model.UserName = name.getText().toString();
                        model.Designation = designation.getText().toString();
                        model.WorkName = Workplace.getText().toString();
                        model.MainOffice = mainOffice.getText().toString();
                        model.MainSalary = MainSalary.getText().toString();
                        boolean isUpdate = mydb.SecondUpdateData(model, model.Id);

                        try {
                            if (isUpdate == true) {
                                Toast.makeText(getBaseContext(), " यशस्वीरित्या बदल केले", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                                startActivity(intent);
                                finish();

                            } else
                                Toast.makeText(getBaseContext(), " त्रुटी", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (Exception ex) {

                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        });
    }
}
