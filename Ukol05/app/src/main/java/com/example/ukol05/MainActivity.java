package com.example.ukol05;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigInteger;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText editCislo;
    Button buttonVypocitat, buttonUkoncit;
    TextView textVysledek;

    @SuppressLint({"SetTextI18n", "DefaultLocale", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        Handler handler = new Handler(Looper.getMainLooper());

        editCislo = findViewById(R.id.editCislo);
        buttonVypocitat = findViewById(R.id.buttonVypocitat);
        textVysledek = findViewById(R.id.textVysledek);
        buttonUkoncit = findViewById(R.id.buttonUkoncit);

        buttonUkoncit.setOnClickListener(v -> finish());

        buttonVypocitat.setOnClickListener(v -> new Thread(() -> {
            try {
                final int number = Integer.parseInt(editCislo.getText().toString());
                if (number < 0) throw new Exception("Číslo musí být nezáporné");

                String formatted;
                if (number <= 170) {
                    BigInteger factorial = new BigInteger("1");
                    for (int i = 2; i <= number; i++) {
                        factorial = factorial.multiply(BigInteger.valueOf(i));
                    }
                    BigInteger finalFactorial = factorial;
                    if (number > 20)
                        formatted = String.format("%6.10e", finalFactorial.doubleValue());
                    else formatted = String.valueOf(finalFactorial);
                } else {
                    formatted = "Infinity";
                }

                handler.post(() -> textVysledek.setText(formatted));
            } catch (final Exception e) {
                String message;
                if (Objects.requireNonNull(e.getMessage()).contains("For input string"))
                    message = "Číslo je příliš velké";
                else message = e.getMessage();
                handler.post(() -> new AlertDialog.Builder(this).setTitle("Chyba").setMessage(message).setPositiveButton(android.R.string.ok, null).setIcon(android.R.drawable.ic_dialog_alert).show());
            }
        }).start());
    }
}