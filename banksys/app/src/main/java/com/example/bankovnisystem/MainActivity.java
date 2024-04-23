package com.example.bankovnisystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CustAdapter mAdapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        boolean loggedBool = false;
        Intent intent = getIntent();


        if(intent.hasExtra("loggedBool"))
        {
            loggedBool = intent.getExtras().getBoolean("loggedBool");
        }

        if(!loggedBool) {
            startLoginActivity();
        } else {
            BankAcc bankAcc = (BankAcc) this.getIntent().getSerializableExtra("bankAcc");
            TextView name = findViewById(R.id.TextView_name);
            TextView accNum = findViewById(R.id.TextView_accNum);
            TextView balance = findViewById(R.id.TextView_balance);


            //todo view doesnt show up on main activity (but hey it doesnt crash the app anymore :D)
            ArrayList<Payment> mDataList;
            if(mAdapter == null) {
                mDataList = dbHelper.getPaymentsFromDB(bankAcc);
                RecyclerView mRecyclerView = findViewById(R.id.recycle_view);

                // if (mDataList != null && !mDataList.isEmpty()) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                mAdapter = new CustAdapter(mDataList);
                mRecyclerView.setAdapter(mAdapter);
                //}
            } else {
                mDataList = dbHelper.getPaymentsFromDB(bankAcc);
                mAdapter.updateDataList(mDataList);
            }

            bankAcc.setData(bankAcc, name, accNum, balance);

            Button buttonPayment = findViewById(R.id.button_newPayment);
            buttonPayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Boolean loggedBool = getIntent().getExtras().getBoolean("loggedBool");
                    Intent pay = new Intent(MainActivity.this, PaymentActivity.class);
                    pay.putExtra("bankAcc", bankAcc);
                    pay.putExtra("loggedBool", loggedBool);
                    MainActivity.this.startActivity(pay);
                }
            });

            Button buttonQR = findViewById(R.id.button_QR);
            buttonQR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, "QR", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

//    protected void onResume() {
//        super.onResume();
//        BankAcc bankAcc = (BankAcc) this.getIntent().getSerializableExtra("bankAcc");
//
//        ArrayList<Payment> mDataList;
//        mDataList = dbHelper.getPaymentsFromDB(bankAcc);
//        RecyclerView mRecyclerView = findViewById(R.id.recycle_view);
//
//        // if (mDataList != null && !mDataList.isEmpty()) {
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mAdapter = new CustAdapter(mDataList);
//        mRecyclerView.setAdapter(mAdapter);
//        mDataList = dbHelper.getPaymentsFromDB(bankAcc);
//        mAdapter.updateDataList(mDataList);
//    }

    private void startLoginActivity() {
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(login);
    }

}