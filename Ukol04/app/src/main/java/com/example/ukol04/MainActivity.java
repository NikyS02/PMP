package com.example.ukol04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        db = new DatabaseHelper(this);
        ListView seznam = findViewById(R.id.seznam);

        String[] values = loadData();
        CustomAdapter adapter = new CustomAdapter(this, values);
        seznam.setAdapter(adapter);

    }

    public String[] loadData() {
        Cursor cursor = db.getAllGames();
        if (cursor == null) return null;
        String[] data = new String[cursor.getCount()];
        int position = 0;
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nazev = cursor.getString(1);
                String zanr = cursor.getString(2);
                String dohrano = cursor.getString(3);
                int odehrano = cursor.getInt(4);
                int postup = cursor.getInt(5);
                int hodnoceni = cursor.getInt(6);
                data[position] = new Hra(id, nazev, zanr, dohrano.equals("1"), odehrano, postup, hodnoceni).toString();
                position++;
            } while (cursor.moveToNext());
            cursor.close();
        }
        return data;
    }

    public void openActivity2(View view) {
        finish();
        startActivity(new Intent(MainActivity.this, MainActivity2.class));
    }

    public void closeApp(View view) {
        finish();
        System.exit(0);
    }
}