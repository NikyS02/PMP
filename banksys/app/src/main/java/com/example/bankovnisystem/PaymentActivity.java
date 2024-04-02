package com.example.bankovnisystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Button buttonContinue = findViewById(R.id.button_sendpayment);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paymentInt = new Intent(PaymentActivity.this, PaymentSummaryActivity.class);

                paymentInt.putExtra("payment", getPaymentSummary());
                PaymentActivity.this.startActivity(paymentInt);
            }
        });

        Button buttonBack = findViewById(R.id.button_back_pay);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment paymentSummary = getPaymentSummary();
                Intent swapToMain = new Intent(PaymentActivity.this, MainActivity.class);
                swapToMain.putExtra("paymentSummary", paymentSummary);
                PaymentActivity.this.startActivity(swapToMain);
            }
        });
    }
    private Payment getPaymentSummary(){
        EditText accNumberET = findViewById(R.id.editTextNumber_AccNum);
        Spinner bankCodeSP = findViewById(R.id.spinner);
        EditText VSET = findViewById(R.id.editTextNumber2_VS);
        EditText SSET = findViewById(R.id.editTextNumber3_SS);
        EditText KSET = findViewById(R.id.editTextNumber4_KS);
        EditText messageForRecieverET = findViewById(R.id.editTextText2_MessageForReciever);
        EditText messageForSenderET = findViewById(R.id.editTextText3_MessageForSender);
        EditText dateET = findViewById(R.id.editTextDate_DatumSplatnosti);

        try {
            return new Payment(
                Integer.parseInt(accNumberET.getText().toString()),
                    Integer.parseInt(bankCodeSP.getItemAtPosition(bankCodeSP.getSelectedItemPosition()).toString()),
                    Integer.parseInt(VSET.getText().toString()),
                    Integer.parseInt(SSET.getText().toString()),
                    Integer.parseInt(KSET.getText().toString()),
                    messageForRecieverET.getText().toString(),
                    messageForSenderET.getText().toString(),
                    dateET.getText().toString()
            );
        } catch (Exception e) {
            return null;
        }

    }
}