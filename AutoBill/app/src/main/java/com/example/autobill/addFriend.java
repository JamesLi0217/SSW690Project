package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.example.autobill.model.Friends;


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

public class addFriend extends AppCompatActivity {

    private EditText etUsers;
    private String userList;
    private Button addConfirm,addCancle;
    private int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        etUsers = findViewById(R.id.users);
        addCancle = findViewById(R.id.friend_cancle);
        addConfirm = findViewById(R.id.friend_confirm);

        addCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(addFriend.this, com.example.autobill.Friends.class);
                startActivity(intent);
                Toast.makeText(addFriend.this,"Adding friedns has been cancled",Toast.LENGTH_LONG).show();
            }
        });

        addConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList = etUsers.getText().toString().trim();
                String[] users = userList.split(",");
                int[] userlist = new int[users.length];
                for (int i = 0; i< users.length; i++){
                    userlist[i] = Integer.parseInt(users[i]);
                }
                if(addFriendtoGroup(userlist)== 500){
                    Toast.makeText(addFriend.this,"Friends have been added to your group",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(addFriend.this,Friends.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(addFriend.this,"Friends have been failed to be added to your group",Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    private int addFriendtoGroup(int[] userlist){

        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .build();
        String groupId = "21";
        Gson gson = new Gson();
        Friends friends = new Friends(groupId,userlist);
        String json = gson.toJson(friends);
        RequestBody requestBody = FormBody.create(json,JSON);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8084/group/users")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("Operation is failed");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                result = response.code();
            }

        });
        return result;
    }
}
