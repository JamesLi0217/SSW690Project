package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.gson.annotations.JsonAdapter;

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
import okhttp3.ResponseBody;


public class Friends extends AppCompatActivity {


//    private TextView result;
//    private Button mBtnFriend;
    private Button mBtnHistory;
    private Button mBtnGroup;
    private Button mBtnBill;
    public String date, title;
    public RecyclerView recyclerview;
    public List<Map<String, Object>> list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
//        recyclerview.addItemDecoration(divider);
        recyclerview = findViewById(R.id.friends_rv);
        recyclerview.setLayoutManager(new LinearLayoutManager(Friends.this));
        recyclerview.setAdapter(new LinearAdapter(Friends.this));

//        mBtnBill = findViewById(R.id.testbill);
//        mBtnBill.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Friends.this,NewBill.class);
//                startActivity(intent);
//            }
//        });

        mBtnGroup = findViewById(R.id.Friend_friends_button_6);
        mBtnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this,SignedIn.class);
                startActivity(intent);
            }
        });

        mBtnHistory = findViewById(R.id.friends_button_8);
        mBtnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this,History.class);
                startActivity(intent);
            }
        });



//        recyclerview = findViewById(R.id.friends_rv);
//        okhttpDate();
    }

//    class MyDecoration extends RecyclerView.ItemDecoration{
//        @Override
//        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            super.getItemOffsets(outRect, view, parent, state);
//            outRect.set(0, 0, 0, getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
//        }
//    }

//    private void okhttpDate() {
//        Log.i("TAG", "--ok--");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client=new OkHttpClient();
//                Request request=new Request.Builder().url("http://10.0.2.2:8085/group/name/3").build();
//                try {
//                    Response response=client.newCall(request).execute();
//                    date = response.body().string();
//
//                    jsonJXDate(date);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    private void jsonJXDate(String date) {
//        if (date!=null) {
//            try {
//                JSONObject jsonObject = new JSONObject(date);
//                JSONArray resultJsonArray = jsonObject.getJSONArray("todos");
//                for (int i=0; i < resultJsonArray.length(); i ++){
//                    jsonObject = resultJsonArray.getJSONObject(i);
//                    Map<String, Object> map = new HashMap<>();
//                    String title = jsonObject.getString("title");
//                    map.put("title", title);
//                    list.add(map);
//                }
//                Message msg=new Message();
//                msg.what = 1;
//                handler.sendMessage(msg);
//            } catch(JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }


//    public Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//
//                    recy_item_Adapter friends_rv=new recy_item_Adapter(list, Friends.this);
//                    recyclerview.setLayoutManager(new LinearLayoutManager(Friends.this));
//                    recyclerview.setAdapter(friends_rv);
//                    recyclerview.addItemDecoration(new DividerItemDecoration(Friends.this, DividerItemDecoration.VERTICAL));
//                    break;
//            }
//        }
//    };
}

// JW的代码
//        result = findViewById(R.id.friends_rv);
//        mBtnFriend = findViewById(R.id.Friend_friends_button_7);
//        mBtnFriend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getFriendList();
//            }
//        });
//
//

//
//    private void getFriendList() {
//        OkHttpClient client = new OkHttpClient.Builder()
//                .build();
//        final Request request = new Request.Builder()
//                .url("http://www.ssaurel.com/tmp/todos")//获取用户Friend的信息
//                .get()
//                .build();
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                result.setText(response.body().string());
//            }
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("Result",e.getMessage());
//            }
//
//        });
//
//    }


