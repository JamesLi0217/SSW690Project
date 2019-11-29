package com.example.autobill;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SignedIn extends AppCompatActivity {


    private Button mBtnSideView;
    private Button mBtnFriend;
    private Button mBtnHistory;
    private Button mBtnCreateGroup;
    private RecyclerView recyclerView;
    private String url = "http://10.0.2.2:8084/group/";
    public List<Map<String, Object>> list=new ArrayList<>();
    public String date, date1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);
        recyclerView = findViewById(R.id.group_rv);
        Intent intent = getIntent();
        final String daima = intent.getStringExtra("key");
        okhttpInfo(daima);

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
                intent.putExtra("key",daima);
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

        mBtnCreateGroup = findViewById(R.id.createGroup);
        mBtnCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignedIn.this, addgroup.class);
                intent.putExtra("key", daima);
                
                startActivity(intent);
            }
        });

    }


    private void okhttpInfo(final String daima) {
        Log.i("TAG", "--OK--");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String path1 = "http://10.0.2.2:8083/users/" + daima + "/AllGroups";
                Request request = new Request
                        .Builder()
                        .url(path1)
                        .addHeader("Authorization", "James eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0Y0BnYW1pbC5jb20iLCJleHAiOjE1NzU0NjkwODF9.NREail4iRgHDunvjix-ve4wDCpr6ZdNM_e0KR4pUgavI2vYWYIOxh8AReo88Nh00sbtkpmA25DFJv7RhipS_Mg")
                        .addHeader("Accept", "application/json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    date1 = response.body().string();
                    jsonJXInfo(date1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void jsonJXInfo(String date1) {
        if(date1 != null) {
            try {
                JSONArray jsonArray = new JSONArray(date1);
                Integer [] group_list = new Integer[jsonArray.length()];
                for (int i=0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int Id = jsonObject.getInt("groupId");
                    group_list[i] = Id;
                }
                for (int m=0; m <group_list.length; m++){
                    System.out.println(group_list[m]);
                    okhttpDate(group_list[m]);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void okhttpDate(final int Id) {
        Log.i("TAG", "--OK--");
        new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String path = url + Id;//userID
                Request request = new Request
                        .Builder()
                        .url(path)
                        .addHeader("Authorization", "James eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0Y0BnYW1pbC5jb20iLCJleHAiOjE1NzU0NjkwODF9.NREail4iRgHDunvjix-ve4wDCpr6ZdNM_e0KR4pUgavI2vYWYIOxh8AReo88Nh00sbtkpmA25DFJv7RhipS_Mg")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    date = response.body().string();
                    jsonJXDate(date);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void  jsonJXDate(String date) {
        if(date != null) {
            try {
                JSONObject jsonObject = new JSONObject(date);
                Map<String, Object> map = new HashMap<>();
                String name = jsonObject.getString("groupName");
                map.put("Name", name);
                JSONArray user = jsonObject.getJSONArray("usersList");
                map.put("List", user);
                list.add(map);
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    GroupAdapter groupAdapter = new GroupAdapter(list, SignedIn.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SignedIn.this));
                    groupAdapter.setOnItemClickListener(new GroupAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(SignedIn.this, BillInfoActivity.class);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(groupAdapter);
                    break;
            }
        }
    };

}


