package com.example.myapplication4.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.myapplication4.R;

public class RecyclerView extends AppCompatActivity {

    private Button mBtnListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        mBtnListView = findViewById(R.id.ListView);
        
    }
}
