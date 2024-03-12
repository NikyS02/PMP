package com.example.porovnaniceny;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonspocitej = findViewById(R.id.button_spocitej);
        buttonspocitej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText cena1 = findViewById(R.id.editTextNumberDecimal_Cena1);
                EditText cena2 = findViewById(R.id.editTextNumberDecimal3_Cena2);

                EditText hmotnost1 = findViewById(R.id.editTextNumberDecimal2_Gramaz1);
                EditText hmotnost2 = findViewById(R.id.editTextNumberDecimal4_Gramaz2);

                double vysledek1 = Double.parseDouble(cena1.getText().toString()) / (Double.parseDouble(hmotnost1.getText().toString()) / 1000);
                double vysledek2 = Double.parseDouble(cena2.getText().toString()) / (Double.parseDouble(hmotnost2.getText().toString()) / 1000);

                TextView vyslednyText1 = findViewById(R.id.textView3);
                TextView vyslednyText2 = findViewById(R.id.textView4);

                vyslednyText1.setText("Cena na kg/l : " + String.format("%.2f", vysledek1) + " Kč");
                vyslednyText2.setText("Cena na kg/l : " + String.format("%.2f", vysledek2) + " Kč");

                if(vysledek1<vysledek2){
                    vyslednyText1.setTextColor(Color.GREEN);
                    vyslednyText2.setTextColor(Color.RED);

                }
                else if(vysledek1>vysledek2){
                    vyslednyText2.setTextColor(Color.GREEN);
                    vyslednyText1.setTextColor(Color.RED);
                }
                else{
                    vyslednyText2.setTextColor(Color.BLACK);
                    vyslednyText1.setTextColor(Color.BLACK);
                }
            }
        });
    }
}