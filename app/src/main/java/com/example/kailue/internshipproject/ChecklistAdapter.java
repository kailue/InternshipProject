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

public class ChecklistAdapter extends RecyclerView.Adapter<ChecklistAdapter.MyViewHolder> {
    private Context mContext;
    private List<Checklist> allCheckList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView type;
        public View view;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            type = (TextView) view.findViewById(R.id.txttype);
        }
    }

    public ChecklistAdapter(Context mContext, List<Checklist> allCheckList) {
        this.mContext = mContext;
        this.allCheckList = allCheckList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.checklist_card, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Checklist checklist = allCheckList.get(position);
        holder.type.setText(checklist.getType());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(checklist.getType());
                if (checklist.getType().equals("Station Audit")) {
                    v.getContext().startActivity(new Intent(mContext, FormActivity.class));
                } else {
                    Toast.makeText(mContext, checklist.getType() + " checklist selected", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public int getItemCount() {
        return allCheckList.size();
    }
}
