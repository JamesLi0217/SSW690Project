package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.example.autobill.model.Group;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



public class Friends extends AppCompatActivity {


    private Button mBtngroup;
    private Button mBtnsideview;
    private Button mBtnaddfriend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        mBtnaddfriend = findViewById(R.id.addfriend);
        mBtngroup = findViewById(R.id.button_6);
        mBtnsideview = findViewById(R.id.sideview);


        mBtnsideview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this,SidesettingActivity.class);
                startActivity(intent);
            }
        });

        mBtngroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this, Group.class);
                startActivity(intent);
            }
        });

        mBtnaddfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this,addFriend.class);
                startActivity(intent);
            }
        });

    }


    }

