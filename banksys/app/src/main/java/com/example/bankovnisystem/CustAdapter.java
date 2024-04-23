package com.example.bankovnisystem;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustAdapter extends RecyclerView.Adapter<CustAdapter.ViewHolder> {
    private ArrayList<Payment> mDataList;
        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                // Define click listener for the ViewHolder's View
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
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.text_row_item, viewGroup, false);
            return new ViewHolder(view);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            Payment data = mDataList.get(position);
            viewHolder.textView.setText(Double.toString(data.getAmmout()));
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }

    @SuppressLint("NotifyDataSetChanged")
    public void updateDataList(ArrayList<Payment> newDataList) {
        mDataList = newDataList;
        notifyDataSetChanged();
    }
    }


