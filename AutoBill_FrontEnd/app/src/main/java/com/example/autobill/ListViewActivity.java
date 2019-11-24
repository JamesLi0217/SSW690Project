package com.example.autobill;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ListViewActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(new ListAdapter(ListViewActivity.this));
    }
}
