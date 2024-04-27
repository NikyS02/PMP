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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
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

            RadioButton showAll = findViewById(R.id.radioButton_all);
            RadioButton showIn = findViewById(R.id.radioButton_in);
            RadioButton showOut = findViewById(R.id.radioButton_out);
            showAll.setChecked(true);
            showIn.setChecked(false);
            showOut.setChecked(false);

            RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    String filter;
                    switch(checkedId) {
                        case R.id.radioButton_all:
                          filter = "all";
                            break;
                        case R.id.radioButton_in:
                            filter = "in";
                            break;
                        case R.id.radioButton_out:
                            filter = "out";
                            break;
                        default:
                            filter = "all";
                    }
                    updateData(filter);
                }
            });

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

            Button buttonLogOut = findViewById(R.id.floatingActionButton);
            buttonLogOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startLoginActivity();
                }
            });

            Button buttonHistory = findViewById(R.id.button_filterPayments);
            buttonHistory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent history = new Intent(MainActivity.this, PaymentHistoryActivity.class);
                    history.putExtra("bankAcc", bankAcc);
                    MainActivity.this.startActivity(history);
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
        updateData("all");
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
            mRecyclerView.setLayoutManager(layoutManager);

            mDataList = dbHelper.getPaymentsFromDB(bankAcc);
            mAdapter = new CustAdapter(mDataList, bankAcc);

            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private void updateData(String filter) {
        if(bankAcc != null) {
            mDataList = dbHelper.getPaymentsFromDB(bankAcc);
            mAdapter.notifyDataSetChangedWithFilter(filter, mDataList);
        }
        }



}