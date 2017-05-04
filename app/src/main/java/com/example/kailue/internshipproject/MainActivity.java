package com.example.kailue.internshipproject;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    private RecyclerView recyclerView;
    private AuditorAdapter adapter;
    private List<Auditor> auditorList;

    private FloatingActionButton floatingActionButton, floatingActionButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Auditors");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        auditorList = new ArrayList<>();
        adapter = new AuditorAdapter(this, auditorList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Checklist.class));
            }
        });

//        prepareAuditors();

    }

    @Override
    public void onStart() {
        super.onStart();

        ValueEventListener auditorListener = new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                auditorList.clear();
                for (DataSnapshot snapShot: dataSnapshot.getChildren()) {
                    Auditor auditor = snapShot.getValue(Auditor.class);
                    auditorList.add(auditor);
                    System.out.println(auditor.getName());
                }
                adapter.notifyDataSetChanged();

            }

            public void onCancelled(DatabaseError databaseError) {

            }
        };

        mDatabase.addValueEventListener(auditorListener);
    }

//    private void prepareAuditors() {
//        String[][] allAuditors = new String[][] {{"Aaron", "a@jet", "qwe"}, {"Bob", "a@jet", "rty"}, {"Clara", "a@jet", "uio"}, {"Dom", "a@jet", "asd"}, {"Elaine", "a@jet", "fgh"}, {"Fish", "b@jet", "jkl"}, {"Guy", "b@jet", "zxc"}, {"Hero", "b@jet", "vbn"}, {"Icarus", "b@jet", "bnm"}, {"Jane", "b@jet", "wer"}};
//        Auditor a;
//
//        for (String[] i: allAuditors) {
//            a = new Auditor(i[0], i[1], i[2], new Date());
//            auditorList.add(a);
//        }
//
//        adapter.notifyDataSetChanged();
//    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;

            if(includeEdge) {
                outRect.left = spacing - column * spacing / spanCount;
                outRect.right = (column + 1) * spacing / spanCount;

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spanCount;
                outRect.right = spacing - (column + 1) * spacing / spanCount;
                if (position >= spanCount) {
                    outRect.top = spacing;
                }
            }
        }
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
