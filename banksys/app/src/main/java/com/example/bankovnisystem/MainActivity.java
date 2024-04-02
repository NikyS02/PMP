package com.example.bankovnisystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // disabled login
        boolean loggedBool = true;
        // todo enable login -> boolean loggedBool = false;

        Intent intent = getIntent();

        if(intent.hasExtra("loggedBool"))
        {
            loggedBool = getIntent().getExtras().getBoolean("loggedBool");
        }

        if(!loggedBool) {
            startLoginActivity();
        } else {
            BankAcc bankAcc = makeTestAcc();

            TextView name = findViewById(R.id.TextView_name);
            TextView accNum = findViewById(R.id.TextView_accNum);
            TextView balance = findViewById(R.id.TextView_balance);

            bankAcc.setData(bankAcc, name, accNum, balance);


            Button buttonPayment = findViewById(R.id.button_newPayment);
            buttonPayment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //todo create design for payment activity
                    Intent pay = new Intent(MainActivity.this, PaymentActivity.class);
                    pay.putExtra("bankAcc", bankAcc);
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

    private BankAcc makeTestAcc() {
        BankAcc bankAcc = new BankAcc();
        return bankAcc;
    }

    private void startLoginActivity() {
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(login);
    }
}