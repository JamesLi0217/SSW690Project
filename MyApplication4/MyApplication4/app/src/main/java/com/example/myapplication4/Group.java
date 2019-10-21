package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Group extends AppCompatActivity {

    private Button mBtnCreateBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        mBtnCreateBill = findViewById(R.id.group_button_1);
        mBtnCreateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Group.this,CreateBill.class);
                startActivity(intent);
            }
        });
    }
}
