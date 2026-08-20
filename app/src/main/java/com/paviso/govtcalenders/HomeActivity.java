package com.paviso.govtcalenders;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.widget.Button;
import com.google.android.material.card.MaterialCardView;

public class HomeActivity extends AppCompatActivity {
    private MaterialCardView janView, febView, marView, aprView;
    private MaterialCardView mayView, juneView, julyView, augView;
    private MaterialCardView septView, octView, novView, decView;

    private MaterialCardView diary, bill;
    private Button profile, holiday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        setupMonthClicks();
        setupNavigationClicks();
    }

    private void initViews() {

        janView = findViewById(R.id.jan);
        febView = findViewById(R.id.feb);
        marView = findViewById(R.id.mar);
        aprView = findViewById(R.id.Apr);

        mayView = findViewById(R.id.may);
        juneView = findViewById(R.id.june);
        julyView = findViewById(R.id.july);
        augView = findViewById(R.id.Aug);

        septView = findViewById(R.id.Sept);
        octView = findViewById(R.id.Oct);
        novView = findViewById(R.id.Nov);
        decView = findViewById(R.id.Dec);

        diary = findViewById(R.id.diary);
        bill = findViewById(R.id.bill);

        profile = findViewById(R.id.nav_profile);
        holiday = findViewById(R.id.nav_holiday);
    }

    private void setupMonthClicks() {

        setupMonthClick(janView, 0);
        setupMonthClick(febView, 1);
        setupMonthClick(marView, 2);
        setupMonthClick(aprView, 3);

        setupMonthClick(mayView, 4);
        setupMonthClick(juneView, 5);
        setupMonthClick(julyView, 6);
        setupMonthClick(augView, 7);

        setupMonthClick(septView, 8);
        setupMonthClick(octView, 9);
        setupMonthClick(novView, 10);
        setupMonthClick(decView, 11);
    }

    private void setupMonthClick(CardView cardView, int month) {

        cardView.setOnClickListener(v -> {

            Intent intent = new Intent(
                    HomeActivity.this,
                    MainActivity.class
            );

            intent.putExtra("value", month);
            startActivity(intent);
        });
    }

    private void setupNavigationClicks() {

        // Tour Diary
        diary.setOnClickListener(v -> {

            Intent intent = new Intent(
                    HomeActivity.this,
                    ReportActivity.class
            );

            startActivity(intent);
        });

        // TA Bill
        bill.setOnClickListener(v -> {

            Intent intent = new Intent(
                    HomeActivity.this,
                    TABillGenerteReports.class
            );

            startActivity(intent);
        });

        // Profile
        profile.setOnClickListener(v -> {

            Intent intent = new Intent(
                    HomeActivity.this,
                    ProfileActivity.class
            );

            startActivity(intent);
        });

        // Government Holidays
        holiday.setOnClickListener(v -> {

            Intent intent = new Intent(
                    HomeActivity.this,
                    HolidaysListActivity.class
            );

            startActivity(intent);
        });
    }
}