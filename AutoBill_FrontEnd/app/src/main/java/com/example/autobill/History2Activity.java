package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class History2Activity extends AppCompatActivity {
    private RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history2);

        recyclerview = findViewById(R.id.history_rv);
        recyclerview.setLayoutManager(new LinearLayoutManager(History2Activity.this));
        recyclerview.setAdapter(new HistoryAdapter(History2Activity.this));
    }
}
