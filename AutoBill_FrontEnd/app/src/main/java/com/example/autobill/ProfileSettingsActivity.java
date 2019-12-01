package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileSettingsActivity extends AppCompatActivity {

    private TextView mAr, mAl, mR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        mAr = findViewById(R.id.pspoint1);
        mAr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileSettingsActivity.this, AddreamrkActivity.class);
                startActivity(intent);
            }
        });

        mAl = findViewById(R.id.pspoint2);
        mAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileSettingsActivity.this, AddlabelActivity.class);
                startActivity(intent);
            }
        });

        mR = findViewById(R.id.pspoint3);
        mR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileSettingsActivity.this, ReportActivity.class);
                startActivity(intent);
            }
        });
    }
}
