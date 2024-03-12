package com.example.bmi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.FormatException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
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

                try{
                    vaha = getVaha();
                    vyska = getVyska();
                    if(vyska == -1 || vaha == -1) throw new FormatException();
                    BMI = spocitejBMI(vaha, vyska);
                    String[] klasifikaceHodnoty = getResources().getStringArray(R.array.klasifikaceHodnoty);
                    int klasifikace = 0;
                    if(BMI >= 18.5 && BMI < 25) klasifikace = 1;
                    else if(BMI >= 25 && BMI < 30) klasifikace = 2;
                    else if(BMI >= 30 && BMI < 35) klasifikace = 3;
                    else if(BMI >= 35 && BMI < 40) klasifikace = 4;
                    else if (BMI >= 40) klasifikace = 5;
                    final TextView textBMI = findViewById(R.id.text_BMI);
                    textBMI.setText("BMI: " + String.format("%.2f", BMI));
                    final TextView textKlasifikace = findViewById(R.id.text_klasifikace);
                    textKlasifikace.setText(klasifikaceHodnoty[klasifikace]);
                }
                catch (FormatException e){
                    Toast.makeText(MainActivity.this, "Špatný formát vstupních hodnot", Toast.LENGTH_SHORT).show();
                    return;
                }




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
//                vaha = Double.parseDouble(textVaha.getText().toString());
                String vahaS = textVaha.getText().toString();

                if(vahaS.matches("")) return -1;
                vaha = Double.parseDouble(vahaS);

            return vaha;
        }

        public double getVyska() {
            double vyska;
            EditText textVaha = findViewById(R.id.editText_vyska);

            String vyskaS = textVaha.getText().toString();
            if(vyskaS.matches("")) return -1;
            vyska = Double.parseDouble(vyskaS);
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