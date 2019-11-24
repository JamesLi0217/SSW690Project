package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autobill.model.User;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignupActivity extends AppCompatActivity {

    private Button mBtnBack;
    private Button mBtnSignUp;
    private EditText etUserName,etUserPwd,etStartDate,etUserEmail;
    private String username;
    private int userstartdate;
    private String userpwd;
    private String useremail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mBtnBack = findViewById(R.id.back);
        mBtnSignUp = findViewById(R.id.su);
        etUserName = findViewById(R.id.username2);
        etStartDate = findViewById(R.id.startdate);
        etUserPwd = findViewById(R.id.password3);
        etUserEmail = findViewById(R.id.email);

        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = etUserName.getText().toString().trim();
                userstartdate = Integer.parseInt(etStartDate.getText().toString().trim());
                userpwd = etUserPwd.getText().toString().trim();
                useremail = etUserEmail.getText().toString().trim();
                SignUp(username,userstartdate,userpwd,useremail);
                Toast.makeText(getApplicationContext(), "Profile Has Been Submitted", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void SignUp(String username, int userstartdate,String userpwd,String useremail){
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .build();


        Gson gson = new Gson();
        User user = new User(username,userstartdate,userpwd,useremail);
        String json = gson.toJson(user);
        RequestBody requestBody = FormBody.create(json,JSON);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8083/users")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("SignUp has failed");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("SignUp is successfull");
            }
        });
    }
}

