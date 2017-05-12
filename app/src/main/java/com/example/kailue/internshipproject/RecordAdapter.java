package com.example.kailue.internshipproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by KaiLue on 03-May-17.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.MyViewHolder> {
    private Context mContext;
    private List<Record> recordList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView station, date, status;
        public View view;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            station = (TextView) view.findViewById(R.id.txtstation);
            date = (TextView) view.findViewById(R.id.txtdate);
            status = (TextView) view.findViewById(R.id.txtflight);
        }
    }

    public RecordAdapter(Context mContext, List<Record> recordList) {
        this.mContext = mContext;
        this.recordList = recordList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_card, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Record record = recordList.get(position);
        holder.station.setText(record.getStation());
        holder.date.setText(record.getDate());
        holder.status.setText(record.getStatus());
    }



    public int getItemCount() {
        return recordList.size();
    }
}
