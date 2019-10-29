package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignedIn extends AppCompatActivity {


    private Button mBtnSideView;
    private Button mBtnFriend;
    private Button mBtnHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);

        mBtnSideView = findViewById(R.id.signedin_button);
        mBtnSideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignedIn.this,SidesettingActivity.class);
                startActivity(intent);
            }
        });

        mBtnFriend = findViewById(R.id.button_7);
        mBtnFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignedIn.this,Friends.class);
                startActivity(intent);
            }
        });

        mBtnHistory = findViewById(R.id.button_8);
        mBtnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignedIn.this,History.class);
                startActivity(intent);
            }
        });
    }

}
