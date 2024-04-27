package com.example.bankovnisystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class PaymentHistoryActivity extends AppCompatActivity {
    private CustAdapter mAdapter;
    private DBHelper dbHelper;
    private BankAcc bankAcc;
    private ArrayList<Payment> mDataList;
    public Spinner SpinMonth;
    public Spinner SpinYear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_history);
        this.bankAcc = (BankAcc) this.getIntent().getSerializableExtra("bankAcc");

        dbHelper = new DBHelper(this);

        SpinMonth = findViewById(R.id.spinner2);
        SpinYear = findViewById(R.id.spinner3);

        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent swapToMain = new Intent(PaymentHistoryActivity.this, MainActivity.class);
                swapToMain.putExtra("loggedBool", true);
                swapToMain.putExtra("bankAcc", bankAcc);

                PaymentHistoryActivity.this.startActivity(swapToMain);
            }
        });



        SpinMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        SpinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        createRecycle();
    }

    private void createRecycle() {

        if (mAdapter == null) {
            RecyclerView mRecyclerView = findViewById(R.id.recycle_View_Histrory);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);

            mDataList = dbHelper.getPaymentsFromDB(bankAcc);
            mAdapter = new CustAdapter(mDataList, bankAcc);

            mRecyclerView.setAdapter(mAdapter);
        }
    }
        @SuppressLint("NotifyDataSetChanged")
        private void updateData() {
            if (bankAcc != null) {
                mDataList = dbHelper.getPaymentsFromDB(bankAcc);
                mAdapter.notifyDataSetChangedWithFilterHistory(
                        SpinMonth.getItemAtPosition(SpinMonth.getSelectedItemPosition()).toString(),
                        SpinYear.getItemAtPosition(SpinYear.getSelectedItemPosition()).toString(),
                        mDataList
                        );
            }
        }
}