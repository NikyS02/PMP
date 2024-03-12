package com.example.porovnaniceny;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

                //double vysledek1 = Double.parseDouble(cena1.getText());
                //double vysledek = Double.parseDouble(cena2.getText());
            }
        });
    }
}