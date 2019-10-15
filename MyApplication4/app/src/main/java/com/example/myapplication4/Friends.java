package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class Friends extends AppCompatActivity {
    private Button mBtnMain2;
    private Button mBtnHistory;
    private SearchView searchView;
    private ListView listView;
    private final String[] strings = {"Thanos","Captain America", "Iron Man"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);


        listView = findViewById(R.id.list_friends_view);
        searchView = findViewById(R.id.search_friends_view);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,strings));


        listView.setTextFilterEnabled(true);
        listView.setVisibility(View.GONE);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(Friends.this,"123",Toast.LENGTH_LONG).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)){
                    listView.clearTextFilter();
                }
                else {
                    Log.d("111",newText);
                }
                return true;

            }
        });

        mBtnMain2 = findViewById(R.id.friends_button_6);
        mBtnMain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this,Main2Activity.class);
                startActivity(intent);
            }
        });

        mBtnHistory = findViewById(R.id.friends_button_8);
        mBtnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Friends.this,history.class);
                startActivity(intent);
            }
        });
    }
}
