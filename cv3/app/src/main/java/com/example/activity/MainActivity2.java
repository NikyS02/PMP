package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button button = findViewById(R.id.button2);
        Data data1 = new Data();
        data1 = (Data) getIntent().getSerializableExtra("data");

        TextView textView = findViewById(R.id.textView2);
        String data = data1.jmeno + data1.vek.toString() + data1.BMI.toString();
        textView.setText(data);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent swap = new Intent(MainActivity2.this, MainActivity.class);
                swap.putExtra("jmeno", "Matěj Pikatní Paštika");
                swap.putExtra("vek", 4);
                swap.putExtra("BMI", 21.3);

                MainActivity2.this.startActivity(swap);

                CharSequence text = "Aktivita 1 byla probuzena a bylo ji předáno řízení :D";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(MainActivity2.this, text, duration);
                toast.show();

            }
        });
    }
}