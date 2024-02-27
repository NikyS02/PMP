package com.example.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.activity.Data;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent data = getIntent();
        String jmeno = data.getStringExtra("jmeno");
        int vek = data.getIntExtra("vek", 0);
        double BMI = data.getDoubleExtra("BMI", 0);

        Data data1 = new Data();
        data1.jmeno = jmeno;
        data1.vek = vek;
        data1.BMI = BMI;

        TextView textView = findViewById(R.id.textView);
        textView.setText(jmeno + vek + BMI);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent swap = new Intent(MainActivity.this, MainActivity2.class);
                swap.putExtra("data", data1);
                MainActivity.this.startActivity(swap);
            }
        });
    }
}