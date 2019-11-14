package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class Friends extends AppCompatActivity {


    private TextView result;
    private Button mBtnFriend;
    private Button mBtnHistory;
    private Button mBtnGroup;
    private Button mBtnBill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        mBtnBill = findViewById(R.id.testbill);
        mBtnBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this,NewBill.class);
                startActivity(intent);
            }
        });

        mBtnGroup = findViewById(R.id.Friend_friends_button_6);
        mBtnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this,SignedIn.class);
                startActivity(intent);
            }
        });

        mBtnHistory = findViewById(R.id.friends_button_8);
        mBtnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this,History.class);
                startActivity(intent);
            }
        });

        result = findViewById(R.id.friends_list);
        mBtnFriend = findViewById(R.id.Friend_friends_button_7);
        mBtnFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFriendList();
            }
        });


    }

    private void getFriendList() {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        final Request request = new Request.Builder()
                .url("http://www.ssaurel.com/tmp/todos")//获取用户Friend的信息
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                result.setText(response.body().string());
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Result",e.getMessage());
            }

        });

    }

    }
