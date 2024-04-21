package com.example.ukol04;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity {

    DatabaseHelper db;
    Button btnSave, btnCancel, btnDelete, btnBack;
    ImageButton btnPlusOdehrano, btnPlusPostup, btnMinusOdehrano, btnMinusPostup;
    EditText editNazev, editOdehrano, editPostup;
    Spinner spinnerZanr;
    ProgressBar progressBarPostup;
    SeekBar seekBarHodnoceni;
    TextView hodnoceniEmoji;
    CheckBox checkBoxDohranoEdit;

    String id;
    Hra hra;

    String[] zanryHer = {"Action", "Adventure", "Role-Playing", "Strategy", "Simulation", "Sports", "Puzzle", "Racing", "Fighting", "Horror"};

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        db = new DatabaseHelper(this);

        btnBack = findViewById(R.id.buttonBack);
        btnSave = findViewById(R.id.buttonSave);
        btnPlusOdehrano = findViewById(R.id.buttonPlusOdehrano);
        btnPlusPostup = findViewById(R.id.buttonPlusPostup);
        btnMinusOdehrano = findViewById(R.id.buttonMinusOdehrano);
        btnMinusPostup = findViewById(R.id.buttonMinusPostup);
        btnCancel = findViewById(R.id.buttonCancel);
        btnDelete = findViewById(R.id.buttonDelete);

        editNazev = findViewById(R.id.editNazev);
        editOdehrano = findViewById(R.id.editOdehrano);
        editPostup = findViewById(R.id.editPostup);

        spinnerZanr = findViewById(R.id.spinnerZanr);

        progressBarPostup = findViewById(R.id.progressBarPostup);

        seekBarHodnoceni = findViewById(R.id.seekBarHodnoceni);

        hodnoceniEmoji = findViewById(R.id.hodnoceniEmoji);

        checkBoxDohranoEdit = findViewById(R.id.checkBoxDohranoEdit);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, zanryHer);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerZanr.setAdapter(adapter);

        id = getIntent().getStringExtra("id");
        if (TextUtils.isEmpty(id)) {
            btnDelete.setVisibility(View.INVISIBLE);
        } else {
            hra = db.getGameById(Integer.parseInt(id));
            editNazev.setText(hra.getNazev());
            int position = new ArrayList<>(Arrays.asList(zanryHer)).indexOf(hra.getZanr());
            if (position != -1) spinnerZanr.setSelection(position);
            editOdehrano.setText(String.valueOf(hra.getOdehrano()));
            checkBoxDohranoEdit.setChecked(hra.isDohrano());
            editPostup.setText(String.valueOf(hra.getPostup()));
            progressBarPostup.setProgress(Integer.parseInt(editPostup.getText().toString()));
            setEmoji(hra.getHodnoceni());
            seekBarHodnoceni.setProgress(hra.getHodnoceni());
        }

        btnSave.setOnClickListener(v -> {
            String nazev = String.valueOf(editNazev.getText());
            String zanr = String.valueOf(spinnerZanr.getSelectedItem());
            int odehrano = 0;
            if (!TextUtils.isEmpty(editOdehrano.getText()))
                odehrano = Integer.parseInt(editOdehrano.getText().toString());
            boolean dohrano = checkBoxDohranoEdit.isChecked();
            int postup = progressBarPostup.getProgress();
            int hodnoceni = seekBarHodnoceni.getProgress();
            if (TextUtils.isEmpty(id)) {
                if (!TextUtils.isEmpty(editNazev.getText())) {
                    db.addGame(new Hra(0, nazev, zanr, dohrano, odehrano, postup, hodnoceni));
                    openMainActivity();
                } else {
                    Toast.makeText(MainActivity2.this, "Je potřeba vyplnit název hry", Toast.LENGTH_LONG).show();
                }
            } else {
                if (!TextUtils.isEmpty(editNazev.getText())) {
                    db.updateGame(Integer.parseInt(id), new Hra(0, nazev, zanr, dohrano, odehrano, postup, hodnoceni));
                    openMainActivity();
                } else {
                    Toast.makeText(MainActivity2.this, "Je potřeba vyplnit název hry", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDelete.setOnClickListener(v -> {

            DialogInterface.OnClickListener yesListener;

            yesListener = (dialog, which) -> {
                db.deleteGame(Integer.parseInt(id));
                openMainActivity();
            };

            message_dialog_yes_no(this, "Smazat záznam?", yesListener);
        });

        btnCancel.setOnClickListener(v -> openMainActivity());
        btnBack.setOnClickListener(v -> openMainActivity());

        btnPlusOdehrano.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(editOdehrano.getText())) {
                editOdehrano.setText(String.valueOf(Integer.parseInt(editOdehrano.getText().toString()) + 1));
            } else {
                editOdehrano.setText("" + 1);
            }
        });

        btnPlusPostup.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(editPostup.getText())) {
                int postup = (Integer.parseInt(editPostup.getText().toString()) + 1);
                if (postup > 100) postup = 100;
                editPostup.setText(String.valueOf(postup));
            } else {
                editPostup.setText("" + 1);
            }
        });

        btnMinusOdehrano.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(editOdehrano.getText())) {
                int postup = (Integer.parseInt(editOdehrano.getText().toString()) - 1);
                if (postup < 0) postup = 0;
                editOdehrano.setText(String.valueOf(postup));
            } else {
                editOdehrano.setText("" + 0);
            }
        });

        btnMinusPostup.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(editPostup.getText())) {
                int postup = (Integer.parseInt(editPostup.getText().toString()) - 1);
                if (postup < 0) postup = 0;
                editPostup.setText(String.valueOf(postup));
            } else {
                editPostup.setText("" + 0);
            }
        });

        editPostup.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                progressBarPostup.setProgress(Integer.parseInt(editPostup.getText().toString()));
            }
        });

        seekBarHodnoceni.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setEmoji(progress);


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void message_dialog_yes_no(Activity activity, String msg, DialogInterface.OnClickListener yesListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        builder.setMessage(msg).setCancelable(false).setPositiveButton("ANO", yesListener).setNegativeButton("Ne", (dialog, id) -> dialog.cancel()).show();
    }

    public void setEmoji(int progress) {
        if (progress <= 10) hodnoceniEmoji.setText("\uD83D\uDE1E");
        else if (progress <= 20) hodnoceniEmoji.setText("\uD83D\uDE14");
        else if (progress <= 30) hodnoceniEmoji.setText("\uD83D\uDE10");
        else if (progress <= 40) hodnoceniEmoji.setText("\uD83D\uDE42");
        else if (progress <= 50) hodnoceniEmoji.setText("\uD83D\uDE03");
        else if (progress <= 60) hodnoceniEmoji.setText("\uD83D\uDE04");
        else if (progress <= 70) hodnoceniEmoji.setText("\uD83D\uDE01");
        else if (progress <= 80) hodnoceniEmoji.setText("\uD83D\uDE0D");
        else if (progress <= 90) hodnoceniEmoji.setText("\uD83E\uDD29");
        else if (progress <= 100) hodnoceniEmoji.setText("\uD83E\uDD73");
    }

    public void openMainActivity() {
        finish();
    }
}