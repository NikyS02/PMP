package com.example.bmi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BMIKlasifikace extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiklasifikace);

        Button buttonZpet = findViewById(R.id.button_back);
        buttonZpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent swap = new Intent(BMIKlasifikace.this, MainActivity.class);
                BMIKlasifikace.this.startActivity(swap);
            }
        });

    }
}