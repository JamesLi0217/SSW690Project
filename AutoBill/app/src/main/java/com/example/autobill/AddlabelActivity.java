package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddlabelActivity extends AppCompatActivity {

    private Button mAlb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addlabel);

        mAlb = findViewById(R.id.alok);
        mAlb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddlabelActivity.this,"Label has been added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
