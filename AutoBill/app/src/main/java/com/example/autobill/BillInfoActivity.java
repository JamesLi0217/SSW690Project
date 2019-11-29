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
import android.widget.LinearLayout;
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

public class BillInfoActivity extends AppCompatActivity {
    public TextView mTv;
    private RecyclerView recyclerView;
    public String url = "http://10.0.2.2:8085/group/";
    public List<Map<String, Object>> list=new ArrayList<>();
    public String date, date1;
    private Button mCreateBill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_info);
        Intent intent = getIntent();
        final Integer pos = intent.getIntExtra("key",9999);

        recyclerView = findViewById(R.id.bill_info_rv);

        mCreateBill = findViewById(R.id.bill_info_add);
        mCreateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillInfoActivity.this, NewBill.class);
                startActivity(intent);
            }
        });
        okhttpGroupInfo(pos);
    }

    private void okhttpGroupInfo(final int pos) {
        Log.i("TAG", "--OK--");
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String path1 = "http://10.0.2.2:8083/users/98971/AllGroups";
                Request request = new Request
                        .Builder()
                        .url(path1)
                        .addHeader("Authorization", "James eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0Y0BnYW1pbC5jb20iLCJleHAiOjE1NzU0NjkwODF9.NREail4iRgHDunvjix-ve4wDCpr6ZdNM_e0KR4pUgavI2vYWYIOxh8AReo88Nh00sbtkpmA25DFJv7RhipS_Mg")
                        .addHeader("Accept", "application/json")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    date1 = response.body().string();
                    jsonJXGroupInfo(date1, pos);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void jsonJXGroupInfo(String date1, final int pos) {
        if(date1 != null) {
            try {
                JSONArray jsonArray = new JSONArray(date1);
                JSONObject jsonObject = jsonArray.getJSONObject(pos);
                int Id = jsonObject.getInt("groupId");
                okhttpInfo(Id);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    private void okhttpInfo(final int Id) {
        Log.i("TAG", "--OK--");
        new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String path = url + Id;//hardcode
                Request request = new Request.Builder().url(path).build();
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

    private void jsonJXInfo(String date) {
        if (date != null) {
            try {
                JSONObject jsonObject = new JSONObject(date);
                JSONArray billlist = jsonObject.getJSONArray("billsList");
                String string =  billlist.toString();
                String str1 = string.substring(1);
                String str2 = str1.substring(0, str1.length() - 1);
                String [] stringArray = str2.split(",");
                int [] bills = new int[stringArray.length];
                for (int i=0; i <stringArray.length; i++) {
                    bills[i] = Integer.parseInt(stringArray[i]);
                }
                for (int m=0; m < bills.length; m++) {
                    okhttpDate(bills[m]);
                }

//                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void okhttpDate(final int billid) {
        Log.i("TAG", "--OK--");
        new Thread(new Runnable() {

            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                String path1 = "http://10.0.2.2:8085/bill/" + billid;
                Request request = new Request.Builder().url(path1).build();
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

    private void  jsonJXDate(String date) {
        if(date != null) {
            try {
                JSONObject jsonObject = new JSONObject(date);
                Map<String, Object> map = new HashMap<>();
                String name = jsonObject.getString("billName");
                String money = jsonObject.getString("amount");
                String time = jsonObject.getString("date");

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
                    BillInfoAdapter billInfoAdapter = new BillInfoAdapter(list, BillInfoActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(BillInfoActivity.this));
                    recyclerView.setAdapter(billInfoAdapter);
                    break;
            }
        }
    };
}
