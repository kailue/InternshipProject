package com.example.kailue.internshipproject;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KaiLue on 09-May-17.
 */

public class FragmentForm1 extends Fragment {

    private Button yes1, no1, na1;

    public static Fragment newInstance(Context context) {
        FragmentForm1 f = new FragmentForm1();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_form_1, null);

        yes1 = (Button) root.findViewById(R.id.yes1);
        no1 = (Button) root.findViewById(R.id.no1);
        na1 = (Button) root.findViewById(R.id.na1);

        yes1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no1.setBackgroundResource(android.R.drawable.btn_default);
                na1.setBackgroundResource(android.R.drawable.btn_default);
                yes1.setBackgroundColor(Color.GREEN);
            }
        });

        no1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes1.setBackgroundResource(android.R.drawable.btn_default);
                na1.setBackgroundResource(android.R.drawable.btn_default);
                no1.setBackgroundColor(Color.RED);
            }
        });

        na1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yes1.setBackgroundResource(android.R.drawable.btn_default);
                no1.setBackgroundResource(android.R.drawable.btn_default);
                na1.setBackgroundColor(Color.YELLOW);
            }
        });


        return root;
    }


}
