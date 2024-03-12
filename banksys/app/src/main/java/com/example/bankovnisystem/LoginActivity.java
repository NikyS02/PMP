package com.example.bankovnisystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        Button ButtonLogin = findViewById(R.id.buttonLogin);

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText EditLogin = findViewById(R.id.editTextTextPersonName);
                EditText EditPasswd = findViewById(R.id.editTextTextPassword);

                String login = EditLogin.getText().toString();
                String passwd = EditPasswd.getText().toString();

                //todo button doesnt button
                if(login == "login" && passwd == "1234") {
                    loginUser(login);
                }
            }


        });

    }
    void loginUser(String login) {
        Intent loggedIntent = new Intent(this, MainActivity.class);
        loggedIntent.putExtra("loggedBool", true);
        startActivity(loggedIntent);
    }
}