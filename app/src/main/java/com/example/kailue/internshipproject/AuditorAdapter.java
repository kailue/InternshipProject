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

public class AuditorAdapter extends RecyclerView.Adapter<AuditorAdapter.MyViewHolder> {
    private Context mContext;
    private List<Auditor> auditorList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, email, department, lastAudDate;
        public View view;

        public MyViewHolder(View view) {
            super(view);
            this.view = view;
            name = (TextView) view.findViewById(R.id.txtname);
            email = (TextView) view.findViewById(R.id.txtemail);
            department = (TextView) view.findViewById(R.id.txtdepartment);
            lastAudDate = (TextView) view.findViewById(R.id.txtdate);
        }
    }

    public AuditorAdapter(Context mContext, List<Auditor> auditorList) {
        this.mContext = mContext;
        this.auditorList = auditorList;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.auditor_card, parent, false);

        return new MyViewHolder(itemView);
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        Auditor auditor = auditorList.get(position);
        holder.name.setText(auditor.getFullName());
//        holder.email.setText(auditor.getEmail());
        SpannableString content = new SpannableString(auditor.getEmail());
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        holder.email.setText(content);
        holder.department.setText(auditor.getDept());
        holder.lastAudDate.setText("01/05/2017");

        holder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {"some@email.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                intent.putExtra(Intent.EXTRA_TEXT, "Mail Body");
                v.getContext().startActivity(Intent.createChooser(intent, ""));

//                Toast.makeText(v.getContext(), "open popup menu to send email", Toast.LENGTH_SHORT).show();
            }
        });

//        holder.lastAudDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), holder.getAdapterPosition() * 10 + " days left", Toast.LENGTH_SHORT).show();
//            }
//        });

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "popup menu with more details", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public int getItemCount() {
        return auditorList.size();
    }
}
