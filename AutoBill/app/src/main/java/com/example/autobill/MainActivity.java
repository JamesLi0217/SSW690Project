package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.autobill.model.User;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button mSu;
    private Button mBtnSignIn;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSu = findViewById(R.id.su);
        mSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册界面
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        mBtnSignIn = findViewById(R.id.si);
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignedIn.class);
                startActivity(intent);
            }
        });

    }
    private void postDataWithParam(){
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody
                .Builder()
                .add("id","1")
                .build();
        Request request = new Request
                .Builder()
                .post(formBody)
                .url("http://localhost:8083/findAllFriends")
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();
            Log.d("androidxx.cn",result);
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}