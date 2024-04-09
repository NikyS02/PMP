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
        DBHelper dbHelper = new DBHelper(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authorize(dbHelper);
            }
        });
    }

    protected void authorize(DBHelper dbHelper) {

        EditText EditLogin = findViewById(R.id.editTextTextPersonName);
        EditText EditPasswd = findViewById(R.id.editTextTextPassword);

        String login = EditLogin.getText().toString();
        String passwd = EditPasswd.getText().toString();

        BankAcc bankAcc = dbHelper.checkLogin(login, passwd);
        if(bankAcc != null) {
            loginUser(bankAcc);
        }
        else {
            Toast.makeText(LoginActivity.this, "Zadali jste špatné údaje", Toast.LENGTH_SHORT).show();
        }
    }

    void loginUser(BankAcc bankAcc) {
        Intent loggedIntent = new Intent(this, MainActivity.class);
        loggedIntent.putExtra("bankAcc", bankAcc);
        loggedIntent.putExtra("loggedBool", true);

        startActivity(loggedIntent);
    }
}