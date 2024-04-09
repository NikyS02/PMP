package com.example.bankovnisystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        BankAcc bankAcc = (BankAcc) this.getIntent().getSerializableExtra("bankAcc");
        DBHelper dbHelper = (DBHelper) this.getIntent().getSerializableExtra("dbHelper");

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authorize(bankAcc, dbHelper);
            }
        });
    }

    void authorize(BankAcc bankAcc, DBHelper dbHelper) {

        EditText EditLogin = findViewById(R.id.editTextTextPersonName);
        EditText EditPasswd = findViewById(R.id.editTextTextPassword);

        String login = EditLogin.getText().toString();
        String passwd = EditPasswd.getText().toString();

        if(login.equals("login") && passwd.equals("1234")) {
            loginUser(login, bankAcc);
        } else {
            Toast.makeText(LoginActivity.this, "Zadali jste špatné údaje", Toast.LENGTH_SHORT).show();
        }
    }

    void loginUser(String login, BankAcc bankAcc) {
        Intent loggedIntent = new Intent(this, MainActivity.class);
        loggedIntent.putExtra("bankAcc", bankAcc);
        loggedIntent.putExtra("loggedBool", true);

        startActivity(loggedIntent);
    }
}