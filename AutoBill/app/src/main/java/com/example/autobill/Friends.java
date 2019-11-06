package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;

public class Friends extends AppCompatActivity {

    private Button mBtnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        mBtnTest = findViewById(R.id.test);
        final TextView textView = findViewById(R.id.friends_list);
// ...

// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://www.google.com";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: "+ response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error.getMessage:   " + error.getMessage());
                System.out.println("error.getCause:   " + error.getCause());
                System.out.println("error.getStackTrace:   " + error.getStackTrace());

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    /*
    private void getWebservice() {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        final Request request = new Request.Builder()
                .url("http://localhost:8083/findAllFriends/1")
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("Result",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                result.setText(response.body().string());
            }
        });

    }
    */

    }

