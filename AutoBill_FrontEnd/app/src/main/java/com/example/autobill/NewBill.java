package com.example.autobill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.autobill.model.Bill;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewBill extends AppCompatActivity {
    private EditText BillName, BillAmount, BillDate, BillReceipt, BillDecription, BillParticipant,BillPayer;
    private String billname, billreceipt, billdescription, billparticipant;
    private Button mBtnConfirm;
    private Button mBtnCancle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_bill);
        BillName = findViewById(R.id.bill_name);
        BillAmount = findViewById(R.id.bill_amount);
        BillDate = findViewById(R.id.bill_date);
        BillReceipt = findViewById(R.id.bill_receipt);
        BillDecription = findViewById(R.id.bill_desciption);
        BillParticipant = findViewById(R.id.bill_participant);
        BillPayer = findViewById(R.id.bill_payer);

        Intent intent = getIntent();
        final Integer pos = intent.getIntExtra("key",9999);
        mBtnCancle =findViewById(R.id.bill_cancle);
        mBtnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewBill.this,BillInfoActivity.class);
                intent.putExtra("key", pos);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Bill Has Been Caneclled ", Toast.LENGTH_SHORT).show();

            }
        });

        mBtnConfirm = findViewById(R.id.bill_confirm);
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                billname = BillName.getText().toString().trim();
                float billamount = Float.parseFloat(BillAmount.getText().toString().trim());
                int billdate = Integer.parseInt(BillDate.getText().toString().trim());
                billreceipt = BillReceipt.getText().toString().trim();
                billdescription = BillDecription.getText().toString().trim();
                billparticipant = BillParticipant.getText().toString().trim();
                String[] participants = billparticipant.split(",");
                int[] userlist = new int[participants.length];
                for (int i = 0; i< participants.length; i++){
                    userlist[i] = Integer.parseInt(participants[i]);
                }
                int billpayer = Integer.parseInt(BillPayer.getText().toString().trim());
                BillConfirm(billname, billamount, billdate, billreceipt, billdescription, userlist,billpayer);
                Toast.makeText(getApplicationContext(), "Bill Has Submitted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void BillConfirm(final String billname, final float billamount, final int billdate, final String billreceipt,
                             final String billdescription, final int[] userlist,final int billpayer) {

        final MediaType JSON = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .build();


        Gson gson = new Gson();
        Bill bill = new Bill(21, billdate,billamount, billname, billdescription,billreceipt, userlist, billpayer);
        //groupID


        String json = gson.toJson(bill);
        RequestBody requestBody = FormBody.create(json,JSON);
        Request request = new Request.Builder()
                .url("http://10.0.2.2:8085/bill")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("Bill Submission Failed");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("Bill Successfully Submitted");

            }
        });
    }

}
