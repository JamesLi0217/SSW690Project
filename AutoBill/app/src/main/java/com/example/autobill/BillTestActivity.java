package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

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

public class BillTestActivity extends AppCompatActivity {
    public TextView mTv;
    private RecyclerView recyclerView;
    public String url = "http://10.0.2.2:8085/bill/";
    public List<Map<String, Object>> list=new ArrayList<>();
    public String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_test);
        recyclerView = findViewById(R.id.bt_rv);
        okhttpDate();
    }
    private void okhttpDate() {
        Log.i("TAG", "--OK--");
        new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String path = url + "8";
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
                String name = jsonObject.getString("billName");
                String money = jsonObject.getString("amount");
                String time = jsonObject.getString("date");
                mTv = findViewById(R.id.bt_money);
                mTv.setText(jsonObject.getString("amount"));

                map.put("Name", name);
                map.put("Money", money);
                map.put("Time", time);
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
                    BillTestAdapter billTestAdapter = new BillTestAdapter(list, BillTestActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(BillTestActivity.this));
                    recyclerView.setAdapter(billTestAdapter);
                    break;
            }
        }
    };
}
