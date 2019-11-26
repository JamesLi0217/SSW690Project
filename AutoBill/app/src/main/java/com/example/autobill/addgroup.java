package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autobill.model.Group;
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

public class addgroup extends AppCompatActivity {
    private EditText groupname, groupuser;
    private Button groupcancle,groupconfirm;
    private String GroupName, GroupUser;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addgroup);

        groupname = findViewById(R.id.groupName);
        groupuser = findViewById(R.id.userlist);
        groupcancle = findViewById(R.id.group_cancle);
        groupconfirm = findViewById(R.id.group_confirm);

        groupcancle = findViewById(R.id.group_cancle);
        groupcancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addgroup.this,SignupActivity.class);
                startActivity(intent);
            }
        });


        groupconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GroupName = groupname.getText().toString().trim();
                GroupUser = groupuser.getText().toString().trim();
                String[] users =GroupUser.split(",");
                int[] userlist = new int[users.length];
                for (int i = 0; i < users.length; i++){
                    userlist[i] = Integer.parseInt(users[i]);
                }
                if(addGroup(GroupName,userlist) == 500 ){
                    Toast.makeText(addgroup.this,"Group has been created",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(addgroup.this,"Fail to create group",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private int addGroup(String GroupName, int[] GroupUser){
        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .build();


        Gson gson = new Gson();
        Group group = new Group(GroupName,GroupUser);
        String json = gson.toJson(group);
        RequestBody requestBody = FormBody.create(json,JSON);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8085/group")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("group is not added");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                result = response.code();
            }
        });
        return result;
    }
}
