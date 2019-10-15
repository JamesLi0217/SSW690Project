package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    private ListView listView;
    private SearchView searchView;
    private Button mbtnFriends;
    private Button mbtnHistory;
    private Button mbtnGroup;


    private final String[] strings = {"Group 1","Group 2", "Group 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView = findViewById(R.id.list_view);
        searchView = findViewById(R.id.search_view);
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,strings));


        listView.setTextFilterEnabled(true);
        listView.setVisibility(View.GONE);
        searchView.setIconifiedByDefault(false);
        searchView.setSubmitButtonEnabled(true);
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(Main2Activity.this,"123",Toast.LENGTH_LONG).show();
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

        mbtnFriends = findViewById(R.id.button_7);
        mbtnFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,Friends.class);
                startActivity(intent);
            }
        });

        mbtnHistory = findViewById(R.id.button_8);
        mbtnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,history.class);
                startActivity(intent);
            }
        });
        mbtnGroup = findViewById(R.id.button_3);
        mbtnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this,Group.class);
                startActivity(intent);
            }
        });

    }
}
