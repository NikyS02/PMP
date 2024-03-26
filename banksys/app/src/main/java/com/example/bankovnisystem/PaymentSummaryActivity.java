package com.example.bankovnisystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PaymentSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_summary);

        Button buttonPay = findViewById(R.id.button_sendpayment);
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pay = new Intent(PaymentSummaryActivity.this, MainActivity.class);
                PaymentSummaryActivity.this.startActivity(pay);
            }
        });

        Button buttonBack = findViewById(R.id.button_back_pay2);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent swapToPay = new Intent(PaymentSummaryActivity.this, PaymentActivity.class);
                PaymentSummaryActivity.this.startActivity(swapToPay);
            }
        });
    }
}