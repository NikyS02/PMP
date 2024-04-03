package com.example.bankovnisystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class PaymentSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_summary);
        BankAcc bankAcc = (BankAcc) this.getIntent().getSerializableExtra("bankAcc");
        Payment paymentSummary = (Payment) this.getIntent().getSerializableExtra("paymentSummary");
        setSummary(paymentSummary);

        Button buttonPay = findViewById(R.id.button_sendpayment);
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pay = new Intent(PaymentSummaryActivity.this, MainActivity.class);
                bankAcc.pay(paymentSummary);
                pay.putExtra("bankAcc", bankAcc);
                PaymentSummaryActivity.this.startActivity(pay);
                Toast.makeText(PaymentSummaryActivity.this,"Platba byla odeslána", Toast.LENGTH_SHORT).show();
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
    void setSummary(Payment paymentSummary) {
        TextView accNumberTW = findViewById(R.id.textView_acc_num);
        TextView bankCodeTW = findViewById(R.id.textView_bank);
        TextView ammoutTW = findViewById(R.id.textView13_ammout);
        TextView VSTW = findViewById(R.id.textView_VS);
        TextView SSTW = findViewById(R.id.textView_SS);
        TextView KSWT = findViewById(R.id.textView_KS);
        TextView messageForRecieverTW = findViewById(R.id.textView_Message_reciever);
        TextView messageForSenderTW = findViewById(R.id.textView_Message_sender);
        TextView dateTW = findViewById(R.id.textView_Date);
        try {
            accNumberTW.setText(new Integer(paymentSummary.getAccNumber()).toString());
            bankCodeTW.setText(new Integer(paymentSummary.getBankCode()).toString());
            ammoutTW.setText(Double.toString(paymentSummary.getAmmout())); //todo ammout = 0
            VSTW.setText(new Integer(paymentSummary.getVS()).toString());
            SSTW.setText(new Integer(paymentSummary.getSS()).toString());
            KSWT.setText(new Integer(paymentSummary.getKS()).toString());
            messageForRecieverTW.setText(paymentSummary.getMessageForReciever());
            messageForSenderTW.setText(paymentSummary.getMessageForSender());
            dateTW.setText(paymentSummary.getDate().toString());
        }
        catch (Exception e){
            Toast.makeText(PaymentSummaryActivity.this, "Err", Toast.LENGTH_SHORT).show();
        }
    }
}