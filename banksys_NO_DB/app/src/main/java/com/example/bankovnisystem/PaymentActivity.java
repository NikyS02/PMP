package com.example.bankovnisystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        BankAcc bankAcc = (BankAcc) this.getIntent().getSerializableExtra("bankAcc");
        boolean loggedBool = (boolean) this.getIntent().getBooleanExtra("loggedBool", false);

        Button buttonContinue = findViewById(R.id.button_sendpayment);
        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paymentInt = new Intent(PaymentActivity.this, PaymentSummaryActivity.class);
                paymentInt.putExtra("bankAcc", bankAcc);
                paymentInt.putExtra("paymentSummary", getPaymentSummary());
                paymentInt.putExtra("loggedBool", loggedBool);

                PaymentActivity.this.startActivity(paymentInt);
            }
        });

        Button buttonBack = findViewById(R.id.button_back_pay);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Payment paymentSummary = getPaymentSummary();
                Intent swapToMain = new Intent(PaymentActivity.this, MainActivity.class);
                swapToMain.putExtra("loggedBool", loggedBool);

                PaymentActivity.this.startActivity(swapToMain);
            }
        });
    }
    private Payment getPaymentSummary(){
        EditText accNumberET = findViewById(R.id.editTextNumber_AccNum);
        Spinner bankCodeSP = findViewById(R.id.spinner);
        EditText ammout = findViewById(R.id.editText_ammount);
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
                    Double.parseDouble(ammout.getText().toString()),
                    Integer.parseInt(VSET.getText().toString()),
                    Integer.parseInt(SSET.getText().toString()),
                    Integer.parseInt(KSET.getText().toString()),
                    messageForRecieverET.getText().toString(),
                    messageForSenderET.getText().toString(),
                    dateET.getText().toString()
            );
        } catch (Exception e) {
            Toast.makeText(PaymentActivity.this, "Err", Toast.LENGTH_SHORT).show();
            return null;
        }

    }
}