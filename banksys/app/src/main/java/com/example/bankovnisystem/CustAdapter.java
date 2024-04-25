package com.example.bankovnisystem;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class CustAdapter extends RecyclerView.Adapter<CustAdapter.ViewHolder> {
    private ArrayList<Payment> mDataList;
    public BankAcc bankAcc;

    public String filter;
    public  DBHelper dbHelper;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_from;
        public TextView textView_ammont;
        public TextView textView_message;
        public TextView textView_date;


        public ViewHolder(View itemView) {
            super(itemView);
            textView_from = itemView.findViewById(R.id.textView_FROM);
            textView_ammont = itemView.findViewById(R.id.textView_Ammount);
            textView_message = itemView.findViewById(R.id.textView_message);
            textView_date = itemView.findViewById(R.id.textView_Date);
        }

        public TextView getTextView_from() {
            return textView_from;
        }

        public TextView getTextView_ammont() {
            return textView_ammont;
        }

        public TextView getTextView_message() {
            return textView_message;
        }

        public TextView getTextView_date() {
            return textView_date;
        }
    }

    public CustAdapter(ArrayList<Payment> paymentList, BankAcc bankAcc_in) {
        mDataList = paymentList;
        bankAcc = bankAcc_in;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("CustAdapter", "onCreateViewHolder called");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_row_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d("CustAdapter", "onBindViewHolder called for position: " + position);
        Payment data = mDataList.get(position);

        // platba ode me někomu
        if (data.getAccNumber() == bankAcc.getAccNumber() && data.getBankCode() == 6942) {
            viewHolder.textView_ammont.setText("+ " + data.getAmmout() + " Kč");
            viewHolder.textView_ammont.setTextColor(Color.GREEN);
            viewHolder.textView_from.setText(data.getAccNumberFrom() + "/" + data.getBankCode());
            viewHolder.textView_message.setText(data.getMessageForSender());
            Log.d("CustAdapter - if", ": in written " + filter);
            // platba od někoho ke mě
        } else if (data.getAccNumberFrom() == bankAcc.getAccNumber() && data.getBankCodeFrom() == 6942) {
            viewHolder.textView_ammont.setText("- " + data.getAmmout() + " Kč");
            viewHolder.textView_ammont.setTextColor(Color.RED);
            viewHolder.textView_from.setText(data.getAccNumber() + "/" + data.getBankCodeFrom());
            viewHolder.textView_message.setText(data.getMessageForReciever());
            Log.d("CustAdapter - if", ": out written " + filter);
        }

        String date;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            date = LocalDate.parse(
                    data.getDate(),
                    DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.UK)
            ).format(
                    DateTimeFormatter.ofPattern("dd.MM.uuuu", Locale.UK)
            );
        } else {
            date = data.getDate();
        }
        viewHolder.textView_date.setText(date + "   ");
    }

    @Override
    public int getItemCount() {
        if (mDataList == null) {
            return 0;
        }
        return mDataList.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void notifyDataSetChangedWithFilter(String filter, ArrayList<Payment> data) {
        mDataList = data;
        mDataList = filterData(filter);
        notifyDataSetChanged();
    }

    private ArrayList<Payment> filterData(String filter) {
        // platba ode me někomu
        if (Objects.equals(filter, "all")) {
            return mDataList;
        }

        ArrayList<Payment> FilteredData = new ArrayList<>();
        for (Payment data : mDataList) {
            switch (filter) {
                case "in":
                    if (data.getAccNumber() == bankAcc.getAccNumber() && data.getBankCode() == 6942) {
                        FilteredData.add(data);
                    }
                    break;
                case "out":
                    if (data.getAccNumberFrom() == bankAcc.getAccNumber() && data.getBankCodeFrom() == 6942) {
                        FilteredData.add(data);
                    }
                    break;
                default:
                    return mDataList;
            }
        }
            return FilteredData;
        }


    @SuppressLint("NotifyDataSetChanged")
    public void notifyDataSetChangedWithFilterHistory(String month, String year, ArrayList<Payment> data) {
        mDataList = data;
        mDataList = filterDataHistory(month, year);
        notifyDataSetChanged();
    }

    private ArrayList<Payment> filterDataHistory(String month, String year) {
        String[] Months = new String[]{"Leden", "Únor", "Březen", "Duben", "Květen", "Červen", "Červenec", "Srpen", "Září", "Říjen", "Listopad", "Prosinec"};
        ArrayList<Payment> FilteredData = new ArrayList<>();
        for (Payment data : mDataList) {
            String date_month = "-1";
            String date_year = "-1";
            try {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    date_month = LocalDate.parse(
                            data.getDate(),
                            DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.UK)
                    ).format(
                            DateTimeFormatter.ofPattern("MM", Locale.UK)
                    );

                    date_year = LocalDate.parse(
                            data.getDate(),
                            DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.UK)
                    ).format(
                            DateTimeFormatter.ofPattern("uuuu", Locale.UK)
                    );
                }
                String month_num = null;
                for(int i = 0; i < 12; i++){
                    if(Months[i].equals(month)) {
                        int j = i + 1;
                        if(i < 9){
                            month_num = "0" + String.valueOf(j);
                        } else {
                            month_num = String.valueOf(j);
                        }
                    }
                }

                if (Objects.equals(month_num, date_month) && Objects.equals(date_year, year)) {
                    Log.d("CustAdp - ", "true");
                        FilteredData.add(data);
                }

            } catch (Exception e) {
                return mDataList;
            }
        }
        return FilteredData;
    }



}



