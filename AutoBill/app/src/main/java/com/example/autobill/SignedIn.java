package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignedIn extends AppCompatActivity {


    private Button mBtnSideView;
    private Button mBtnFriend;
    private Button mBtnHistory;
    private Button test;
    private TextView result;


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

        test = findViewById(R.id.button_6);
        result = findViewById(R.id.textview);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGroupList();
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
    private void getGroupList(){
        //this function will show the user groups.
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        final Request request = new Request.Builder()
                .url("http://10.0.2.2:8084/group/jack")
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("Result",e.getMessage());
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                result.setText(response.body().string());
            }
        });

    }



    }


