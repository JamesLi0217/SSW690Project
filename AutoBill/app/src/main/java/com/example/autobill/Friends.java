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


public class Friends extends AppCompatActivity {

    private Button mBtngroup, mBtnaddfriend, mBtnsideview;

    private RecyclerView recyclerView;
    private String url = "http://10.0.2.2:8083/users/";
    public List<Map<String, Object>> list=new ArrayList<>();
    public String date, date1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        Intent intent = getIntent();
        final String daima1 = intent.getStringExtra("key");

        recyclerView = findViewById(R.id.friends_rv);
        okhttpInfo(daima1);

        mBtnsideview = findViewById(R.id.sideview);
        mBtnsideview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this, SidesettingActivity.class);
                startActivity(intent);
            }
        });

        mBtnaddfriend = findViewById(R.id.addfriend);
        mBtnaddfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this, addFriend.class);
                startActivity(intent);
            }
        });

        mBtngroup = findViewById(R.id.button_6);
        mBtngroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this, SignedIn.class);
                startActivity(intent);
            }
        });
    }

    private void okhttpInfo(final String daima1) {
        Log.i("TAG", "--OK--");
        new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String path = url + daima1 + "/Friends";//UserID
                Request request = new Request
                        .Builder()
                        .url(path)
                        .addHeader("Authorization", "James eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0Y0BnYW1pbC5jb20iLCJleHAiOjE1NzU0NjkwODF9.NREail4iRgHDunvjix-ve4wDCpr6ZdNM_e0KR4pUgavI2vYWYIOxh8AReo88Nh00sbtkpmA25DFJv7RhipS_Mg")
                        .addHeader("Accept", "application/json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    date = response.body().string();
                    jsonJXInfo(date);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void  jsonJXInfo(String date) {
        if(date != null) {
            try {
                JSONArray jsonArray = new JSONArray(date);
                Integer [] friend_list = new Integer[jsonArray.length()];
                for (int i=0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("friendId");
                    friend_list[i] = id;
                }
                for (int m=0; m <friend_list.length; m++){
                    okhttpDate(friend_list[m]);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void okhttpDate(final int id) {
        Log.i("TAG", "--OK--");
        new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String path1 = "http://10.0.2.2:8083/users/" + id;
                Request request = new Request
                        .Builder()
                        .url(path1)
                        .addHeader("Authorization", "James eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0Y0BnYW1pbC5jb20iLCJleHAiOjE1NzU0NjkwODF9.NREail4iRgHDunvjix-ve4wDCpr6ZdNM_e0KR4pUgavI2vYWYIOxh8AReo88Nh00sbtkpmA25DFJv7RhipS_Mg")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    date1 = response.body().string();
                    jsonJXDate(date1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void  jsonJXDate(String date1) {
        if(date != null) {
            try {
                JSONObject jsonObject = new JSONObject(date1);
                Map<String, Object> map = new HashMap<>();
                String name = jsonObject.getString("userName");
                map.put("Name", name);
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
                    FriendsAdapter friendsAdapter = new FriendsAdapter(list, Friends.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Friends.this));
                    recyclerView.setAdapter(friendsAdapter);
                    break;
            }
        }
    };
}




