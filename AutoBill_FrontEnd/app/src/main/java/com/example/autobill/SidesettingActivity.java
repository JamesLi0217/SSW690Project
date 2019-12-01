package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SidesettingActivity extends AppCompatActivity {

    private Button mEp, mPm, mSs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sidesetting);

        mEp = findViewById(R.id.ep);
        mEp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SidesettingActivity.this, EditprofileActivity.class);
                startActivity(intent);
            }
        });

        mPm = findViewById(R.id.pm);
        mPm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SidesettingActivity.this, PaymentmethodsActivity.class);
                startActivity(intent);
            }
        });

        mSs = findViewById(R.id.s);
        mSs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SidesettingActivity.this, ProfileSettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
