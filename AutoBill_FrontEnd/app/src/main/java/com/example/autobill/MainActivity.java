package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autobill.model.LoginUser;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private Button mSu;
    private Button mBtnSignIn;
    private EditText useremail, pwd;
    private String userEmail, userPassword;
    private String Code;
    private int returnCode;

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


        useremail = findViewById(R.id.username1);
        pwd = findViewById(R.id.password);
        mBtnSignIn = findViewById(R.id.si);
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = useremail.getText().toString().trim();
                userPassword = pwd.getText().toString().trim();
                try {
                    checkSignin(userEmail, userPassword);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Code != null){
                    login(Code);
                    if(returnCode == 200){
                        Intent intent = new Intent(MainActivity.this, SignedIn.class);
                        intent.putExtra("key",Code);
                        startActivity(intent);
                    }
                }
            }
        });

    }


    private String checkSignin(final String userMail, final String pwd) throws IOException {
        //this function will show the user groups.
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Gson gson = new Gson();
        if (TextUtils.isEmpty(userEmail) || TextUtils.isEmpty(userPassword)) {
            Toast.makeText(MainActivity.this, "Email Address and Password should not be empty", Toast.LENGTH_SHORT).show();
        } else {
            LoginUser user = new LoginUser(userEmail, userPassword);
            String json = gson.toJson(user);
            RequestBody requestBody = RequestBody.create(json, JSON);
            final Request request = new Request.Builder()
                    .url("http://10.0.2.2:8083/users/login")
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.d("result", e.getMessage());
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    Code = response.header("UserID");
                }
            });
        }
        return Code;
    }

    private int login(String code) {
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        String http = "http://10.0.2.2:8083/users/" + code;
        final Request request = new Request.Builder()
                .get()
                .url(http)
                .addHeader("Authorization", "James eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0Y0BnYW1pbC5jb20iLCJleHAiOjE1NzU0NjkwODF9.NREail4iRgHDunvjix-ve4wDCpr6ZdNM_e0KR4pUgavI2vYWYIOxh8AReo88Nh00sbtkpmA25DFJv7RhipS_Mg")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("result", e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                returnCode = response.code();
            }
        });
        return returnCode;
    }

}



