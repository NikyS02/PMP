package com.example.porovnaniceny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.os.Handler;
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

        Button buttonspocitej = findViewById(R.id.button_spocitej);
        buttonspocitej.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText cena1 = findViewById(R.id.editTextNumberDecimal_Cena1);
                EditText cena2 = findViewById(R.id.editTextNumberDecimal3_Cena2);

                EditText hmotnost1 = findViewById(R.id.editTextNumberDecimal2_Gramaz1);
                EditText hmotnost2 = findViewById(R.id.editTextNumberDecimal4_Gramaz2);

                double vysledek1 = 0;
                double vysledek2 = 0;

                try{
                    double hmot1=Double.parseDouble(hmotnost1.getText().toString());
                    double hmot2=Double.parseDouble(hmotnost2.getText().toString());
                    if (hmot1 == 0 || hmot2 == 0){
                        throw new NumberFormatException();
                    }
                    vysledek1 = Double.parseDouble(cena1.getText().toString()) / (Double.parseDouble(hmotnost1.getText().toString()) / 1000);
                    vysledek2 = Double.parseDouble(cena2.getText().toString()) / (Double.parseDouble(hmotnost2.getText().toString()) / 1000);
                }
                catch (NumberFormatException e){
                    Toast.makeText(MainActivity.this,"Špatný formát vstupních hodnot",Toast.LENGTH_LONG).show();
                    return;
                }


                TextView vyslednyText1 = findViewById(R.id.textView3);
                TextView vyslednyText2 = findViewById(R.id.textView4);

                vyslednyText1.setText("cena na kg/l : " + String.format("%.2f", vysledek1));
                vyslednyText2.setText("cena na kg/l : " + String.format("%.2f", vysledek2));

                TextView produkt1 = findViewById(R.id.textView_c1);
                TextView produkt2 = findViewById(R.id.textView2);

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);

                if(vysledek1<vysledek2){
                    vyslednyText1.setTextColor(Color.GREEN);
                    vyslednyText2.setTextColor(Color.RED);
                    produkt1.setTextColor(Color.GREEN);
                    produkt2.setTextColor(Color.RED);
                    builder.setMessage("Produkt 1 je levnější: " + String.format("%.2f", vysledek1) + " za 1 kg/l");
                }
                else if(vysledek1>vysledek2){
                    vyslednyText2.setTextColor(Color.GREEN);
                    vyslednyText1.setTextColor(Color.RED);
                    produkt2.setTextColor(Color.GREEN);
                    produkt1.setTextColor(Color.RED);
                    builder.setMessage("Produkt 2 je levnější: " + String.format("%.2f", vysledek2) + " za 1 kg/l");
                }
                else{
                    vyslednyText2.setTextColor(Color.BLACK);
                    vyslednyText1.setTextColor(Color.BLACK);
                    produkt2.setTextColor(Color.BLACK);
                    produkt1.setTextColor(Color.BLACK);
                    builder.setMessage("Produkty mají stejnou cenu");
                }

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        alertDialog.dismiss();
                    }
                },7000);
            }
        });
    }
}