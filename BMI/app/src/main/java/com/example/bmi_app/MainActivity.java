package com.example.bmi_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Button buttonSpocitejBMI = findViewById(R.id.button_spocitej);
        // ˘ crashes
        buttonSpocitejBMI.setOnClickListener(this);
        setContentView(R.layout.activity_main);
    }

    @Override
        public void onClick(View view) {
            double BMI, vaha, vyska;
            vaha = getVaha();
            vyska = getVyska();

            BMI = spocitejBMI(vaha, vyska);
            final TextView textBMI = findViewById(R.id.text_BMI);
            textBMI.setText(String.format("%f", BMI));
        }

        public double getVaha() {
            double vaha;
            final TextView textVaha = findViewById(R.id.text_vaha);
            vaha = Double.parseDouble(textVaha.getText().toString());
            return vaha;
        }

        public double getVyska() {
            double vyska;
            final TextView textVaha = findViewById(R.id.text_vyska);
            vyska = Double.parseDouble(textVaha.getText().toString());
            return vyska;
        }

    //BMI = tělesná váha (kg) / tělesná výška na druhou (m)
    public double spocitejBMI(double vaha, double vyska) {
        double BMI;
        BMI = vaha / vyska + vyska;

        return BMI;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}