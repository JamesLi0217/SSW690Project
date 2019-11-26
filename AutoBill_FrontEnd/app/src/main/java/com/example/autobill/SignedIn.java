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
    private GroupAdapter mAdapter;
    private String url = "http://10.0.2.2:8084/group/";
    public List<Map<String, Object>> list=new ArrayList<>();
    public String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);
        recyclerView = findViewById(R.id.group_rv);
        Intent intent = getIntent();
        String daima = intent.getStringExtra("key");
        System.out.println(daima);
        okhttpDate();

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
                startActivity(intent);
            }
        });

    }

    private void okhttpDate() {
        Log.i("TAG", "--OK--");
        new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String path = url + "13";//userID
                Request request = new Request.Builder().url(path).build();
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


