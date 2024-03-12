package com.example.bankovnisystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Boolean loggedBool = false;

        Intent intent = getIntent();

        if(intent.hasExtra("loggedBool"))
        {
            loggedBool = getIntent().getExtras().getBoolean("loggedBool");

        }
        if(!loggedBool) {
            startLoginActivity();
        }


    }

    private void startLoginActivity() {
        Intent login = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(login);
    }
}