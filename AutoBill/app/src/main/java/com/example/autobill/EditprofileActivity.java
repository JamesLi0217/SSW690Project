package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditprofileActivity extends AppCompatActivity {

    private Button mBtnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        mBtnBack = findViewById(R.id.back1);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditprofileActivity.this,SidesettingActivity.class);
                startActivity(intent);
            }
        });
    }
}
