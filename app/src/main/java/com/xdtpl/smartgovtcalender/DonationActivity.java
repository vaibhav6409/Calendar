package com.xdtpl.smartgovtcalender;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DonationActivity extends AppCompatActivity {

    private EditText amount;
    private Button donation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        donation = findViewById(R.id.donation);
        amount = findViewById(R.id.amount);

        donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String amountValue = amount.getText()
                        .toString()
                        .trim();

                if (amountValue.isEmpty()) {
                    amount.requestFocus();
                    amount.setError("Field cannot be empty");
                    return;
                }

                Toast.makeText(
                        DonationActivity.this,
                        "Payment service is being updated.",
                        Toast.LENGTH_SHORT
                ).show();

                // TODO:
                // Integrate the latest Paytm payment SDK here.
            }
        });
    }
}