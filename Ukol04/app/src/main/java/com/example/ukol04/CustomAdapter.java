package com.example.ukol04;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    private static class ViewHolder {
        TextView idHry;
        TextView firstLine;
        TextView secondLine;
        CheckBox checkBoxDohrano;
    }

    public CustomAdapter(Context context, String[] values) {
        super(context, R.layout.item_listview, values);
        this.context = context;
        this.values = values;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_listview, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.idHry = convertView.findViewById(R.id.idHry);
            viewHolder.firstLine = convertView.findViewById(R.id.firstLine);
            viewHolder.secondLine = convertView.findViewById(R.id.secondLine);
            viewHolder.checkBoxDohrano = convertView.findViewById(R.id.checkBoxDohrano);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String[] data = values[position].split(";");

        viewHolder.idHry.setText(data[0]);
        viewHolder.firstLine.setText(data[1]);
        viewHolder.secondLine.setText("Odehráno hodin: " + data[4]);
        viewHolder.checkBoxDohrano.setChecked(data[3].equals("true"));

        if (position % 2 == 0) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.first));
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.second));
        }

        viewHolder.checkBoxDohrano.setOnCheckedChangeListener((buttonView, isChecked) -> {
            DatabaseHelper db = new DatabaseHelper(context);
            int id = Integer.parseInt(viewHolder.idHry.getText().toString());
            Hra hra = db.getGameById(id);
            hra.setDohrano(!hra.isDohrano());
            db.updateGame(id, hra);
        });

        viewHolder.firstLine.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity2.class);
            intent.putExtra("id", viewHolder.idHry.getText().toString());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

        viewHolder.secondLine.setOnClickListener(v -> {
            Intent intent = new Intent(context, MainActivity2.class);
            intent.putExtra("id", viewHolder.idHry.getText().toString());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });


        return convertView;
    }

}


