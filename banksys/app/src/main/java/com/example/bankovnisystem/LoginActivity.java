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

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authorize();
            }
        });
    }

    void authorize() {
        EditText EditLogin = findViewById(R.id.editTextTextPersonName);
        EditText EditPasswd = findViewById(R.id.editTextTextPassword);

        String login = EditLogin.getText().toString();
        String passwd = EditPasswd.getText().toString();

        if(login.equals("login") && passwd.equals("1234")) {
            loginUser(login);
        } else {
            Toast.makeText(LoginActivity.this, "Zadali jste špatné údaje", Toast.LENGTH_SHORT).show();
        }
    }

    void loginUser(String login) {
        Intent loggedIntent = new Intent(this, MainActivity.class);
        loggedIntent.putExtra("loggedBool", true);
        startActivity(loggedIntent);
    }
}