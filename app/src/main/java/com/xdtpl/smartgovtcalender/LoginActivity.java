package com.xdtpl.smartgovtcalender;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteOpenHelper mydb;

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
   final EditText mail =  findViewById(R.id.ed1);
    final   EditText  p1 = findViewById(R.id.ed2);
        TextView registration = findViewById(R.id.reg);
        Button login = (Button) findViewById(R.id.login);
        mydb=new DatabaseHelper(this);
        db = mydb.getReadableDatabase();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String email =  mail.getText().toString();
                String pass = p1.getText().toString();

                cursor = db.rawQuery("SELECT *FROM " + DatabaseHelper.SECOND_TABLE_NAME + " WHERE " + DatabaseHelper.col_no_5 + "=? AND " + DatabaseHelper.col_no_6 + "=?", new String[]{email, pass});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {
                        Toast.makeText(getApplicationContext(), "यशस्वीरित्या लॉगिन झाले", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this ,MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "त्रुटी", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this ,RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}
