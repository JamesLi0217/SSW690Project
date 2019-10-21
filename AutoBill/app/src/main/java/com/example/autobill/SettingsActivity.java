package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    private TextView mGs;
    private TextView mAs;
    private TextView mFb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        mGs = findViewById(R.id.spoint2);
        mGs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, GeneralsetActivity.class);
                startActivity(intent);
            }
        });

        mAs = findViewById(R.id.spoint1);
        mAs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, AccountsetActivity.class);
                startActivity(intent);
            }
        });

        mFb = findViewById(R.id.spoint4);
        mFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, FeedbackActivity.class);
                startActivity(intent);
            }
        });
    }
}
