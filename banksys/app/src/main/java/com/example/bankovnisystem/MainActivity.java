package com.example.bankovnisystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CustAdapter mAdapter;
    private DBHelper dbHelper;
    private BankAcc bankAcc;
    private ArrayList<Payment> mDataList;

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
            this.bankAcc = bankAcc;
            TextView name = findViewById(R.id.TextView_name);
            TextView accNum = findViewById(R.id.TextView_accNum);
            TextView balance = findViewById(R.id.TextView_balance);


            //todo view doesnt show up on main activity (but hey it doesnt crash the app anymore :D)
           createRecycle();

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

    protected void onResume() {
        super.onResume();
//        createRecycle();
        updateData();
    }

    private void startLoginActivity() {
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(login);
    }

    private void createRecycle() {
        ArrayList<Payment> mDataList;

        if(mAdapter == null) {
            RecyclerView mRecyclerView = findViewById(R.id.recycle_view);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerView.setLayoutManager(layoutManager);

            mDataList = dbHelper.getPaymentsFromDB(bankAcc);
            Log.d("MainActivity", "Data list size: " + mDataList.size());
            mAdapter = new CustAdapter(mDataList);

            mRecyclerView.setAdapter(mAdapter);
            //updateData();
        }
//        } else {
//            Toast.makeText(MainActivity.this, "createRycycle - adapter null", Toast.LENGTH_SHORT).show();
//            mDataList = dbHelper.getPaymentsFromDB(bankAcc);
//            mAdapter.updateDataList(mDataList);
//        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateData() {
        if(bankAcc != null) {
            mDataList = dbHelper.getPaymentsFromDB(bankAcc);
//            ArrayList<Payment> newData = dbHelper.getPaymentsFromDB(bankAcc);
//            mDataList.clear(); // Clear the existing data
//            mDataList.addAll(newData); // Add the new data
            mAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
        }
        }

}