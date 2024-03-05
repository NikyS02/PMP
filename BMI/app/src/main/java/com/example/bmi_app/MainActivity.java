package com.example.bmi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSpocitejBMI = findViewById(R.id.button_spocitej);
        buttonSpocitejBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double BMI, vaha, vyska;
                vaha = getVaha();
                vyska = getVyska();

                BMI = spocitejBMI(vaha, vyska);
                final TextView textBMI = findViewById(R.id.textView_BMI);
                textBMI.setText(String.format("%.2f", BMI));
            }
        });

        Button buttonKlasifikace = findViewById(R.id.buttonKlasifikace);
        buttonKlasifikace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent swap = new Intent(MainActivity.this, BMIKlasifikace.class);
                MainActivity.this.startActivity(swap);
            }
        });


    }

        public double getVaha() {
            double vaha;
            EditText textVaha = findViewById(R.id.editText_vaha);
            try {
//                vaha = Double.parseDouble(textVaha.getText().toString());
                String vahaS = textVaha.getText().toString();
                vaha = Double.parseDouble(vahaS);
            } catch (NullPointerException e) {
                Toast toast = Toast.makeText(MainActivity.this, "Null Pointer", Toast.LENGTH_SHORT);
                toast.show();
                return -1;
            } catch (NumberFormatException  e) {
                Toast toast = Toast.makeText(MainActivity.this, "Zadaná váha má špatný formát! >:(", Toast.LENGTH_SHORT);
                toast.show();
                return -1;
            }

            return vaha;
        }

        public double getVyska() {
            double vyska;
            EditText textVaha = findViewById(R.id.editText_vyska);
            try {
                vyska = Double.parseDouble(textVaha.getText().toString());
            } catch (NumberFormatException  e) {
                Toast toast = Toast.makeText(MainActivity.this, "Zadaná výška má špatný formát! >:(", Toast.LENGTH_SHORT);
                toast.show();
                return -1;
            }
            return vyska;
        }

    //BMI = tělesná váha (kg) / tělesná výška na druhou (m)
    public double spocitejBMI(double vaha, double vyska) {
        double BMI;
        BMI = vaha / (vyska * vyska);

        return BMI;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}