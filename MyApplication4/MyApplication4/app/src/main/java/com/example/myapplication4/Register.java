package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register extends AppCompatActivity {

    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mBtnRegister = findViewById(R.id.button_1);
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("info","You've successfully registered and are able to login in now !");
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
    }
}
