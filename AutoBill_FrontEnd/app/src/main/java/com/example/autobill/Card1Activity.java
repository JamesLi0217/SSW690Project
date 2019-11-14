package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Card1Activity extends AppCompatActivity {

    private TextView mRc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card1);

        mRc = findViewById(R.id.remove);
        mRc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Card1Activity.this,"Card has been removed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
