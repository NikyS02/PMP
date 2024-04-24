package com.example.bankovnisystem;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustAdapter extends RecyclerView.Adapter<CustAdapter.ViewHolder> {
    private ArrayList<Payment> mDataList;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.textView);
            }

            public TextView getTextView() {
                return textView;
            }
        }

        public CustAdapter(ArrayList<Payment> paymentList) {
            mDataList = paymentList;
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
            viewHolder.textView.setText(Double.toString(data.getAmmout()));
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

    }


