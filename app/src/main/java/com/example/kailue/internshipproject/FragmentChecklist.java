package com.example.kailue.internshipproject;

import android.content.Context;
import android.content.res.Resources;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KaiLue on 09-May-17.
 */

public class FragmentChecklist extends Fragment {

    private DatabaseReference database;

    private RecyclerView recyclerView;
    private ChecklistAdapter checklistAdapter;
    private List<Checklist> allCheckList;

    public static Fragment newInstance(Context context) {
        FragmentChecklist f = new FragmentChecklist();

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_checklist, null);

        MyDatabase myDatabase = (MyDatabase) getActivity().getApplicationContext();
        database = myDatabase.getDatabase().child("Checklists");

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_checklist);

        allCheckList = new ArrayList<>();
        checklistAdapter = new ChecklistAdapter(getActivity(), allCheckList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(checklistAdapter);

//        prepareChecklist();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        ValueEventListener auditorListener = new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                allCheckList.clear();
                for (DataSnapshot snapShot: dataSnapshot.getChildren()) {
                    Checklist checklist = new Checklist(snapShot.getKey());
                    allCheckList.add(checklist);
                }

                checklistAdapter.notifyDataSetChanged();

            }

            public void onCancelled(DatabaseError databaseError) {

            }
        };

        database.addValueEventListener(auditorListener);
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to  pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

//    private void prepareChecklist() {
//        String[] allChecklists = {"Station Audit", "Line Audit"};
//        Checklist c;
//
//        for (String i: allChecklists) {
//            c = new Checklist(i);
//            allCheckList.add(c);
//        }
//
//        checklistAdapter.notifyDataSetChanged();
//    }
}
