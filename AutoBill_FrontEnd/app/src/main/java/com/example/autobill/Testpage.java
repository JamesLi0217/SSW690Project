package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Testpage extends AppCompatActivity {
    private TextView mTv;
    public String date;
    public TextView datejson;
    public RecyclerView recyclerView;
    public List<Map<String, Object>> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testpage);
        recyclerView = findViewById(R.id.testpage_rv);
        okhttpDate();

    }

    private void okhttpDate() {
        Log.i("TAG", "--OK--");
        new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://10.0.2.2:8084/group/13").build();
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
                String id = jsonObject.getString("groupId");

                map.put("Name", name);
                map.put("ID", id);
                JSONArray userid = jsonObject.getJSONArray("usersList");
                map.put("List", userid);
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
                    TestpageAdapter testpageAdapter = new TestpageAdapter(list, Testpage.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Testpage.this));
                    recyclerView.setAdapter(testpageAdapter);
                    break;
            }
        }
    };




}
