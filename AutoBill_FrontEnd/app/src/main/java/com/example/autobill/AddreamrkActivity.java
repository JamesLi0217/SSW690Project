package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddreamrkActivity extends AppCompatActivity {

    private Button mArb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreamrk);

        mArb = findViewById(R.id.arok);
        mArb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddreamrkActivity.this,"Remark has been added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
