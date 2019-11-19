package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.autobill.model.User;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button mSu;
    private Button mBtnSignIn;
    private EditText username;
    private EditText pwd;
    private String UserName,Pwd;
    private String is;
    private Button mBtnFp;

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


        username = findViewById(R.id.username1);
        pwd = findViewById(R.id.password);
        mBtnSignIn = findViewById(R.id.si);
        //检查用户名密码，如果正确，则成功登录。
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,SignedIn.class);
                startActivity(intent);
                //UserName = username.getText().toString().trim();
                //Pwd = pwd.getText().toString().trim();
                //checkSignin(UserName,Pwd);
            }
        });

        mBtnFp = findViewById(R.id.fp);
        mBtnFp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Testpage.class);
                startActivity(intent);
            }
        });

    }

    private void checkSignin(final String userName,final String pwd){
        //this function will show the user groups.
        new Thread(new Runnable() {
            @Override
            public void run() {
                FormBody body = new FormBody.Builder()
                        .add("username1",userName)
                        .add("pwd",pwd)
                        .build();
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:8083/login")
                        .post(body)
                        .build();
                OkHttpClient client = new OkHttpClient();
                Callback callback = new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                    }
                };

                }

        });
    }


}