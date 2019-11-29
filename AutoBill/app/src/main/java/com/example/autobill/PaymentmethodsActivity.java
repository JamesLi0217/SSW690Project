package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PaymentmethodsActivity extends AppCompatActivity {

    private TextView mBoa;
    private Button mBtnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymentmethods);

        mBoa = findViewById(R.id.card1);
        mBoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentmethodsActivity.this, Card1Activity.class);
                startActivity(intent);
            }
        });

        mBtnBack = findViewById(R.id.pmback);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentmethodsActivity.this,SidesettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
