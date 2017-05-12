package com.example.kailue.internshipproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FormActivity extends AppCompatActivity {

    private Button previous, next;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        previous = (Button) findViewById(R.id.btnprevious);
        next = (Button) findViewById(R.id.btnnext);

        btnState();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new FragmentForm1();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.fragment2, newFragment);
                transaction.addToBackStack(null);

                transaction.commit();

                count += 1;
                btnState();
            }
        });



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        prepareFragment();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (count > 0) {
            count -= 1;
        }

        btnState();
    }

    private void btnState() {
        if (count == 0) {
            previous.setText("Back");
            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        } else {
            previous.setText("Previous");
            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        getSupportFragmentManager().popBackStack();
                        count -= 1;
                    }
                    btnState();
                }
            });
        }
    }

    private void prepareFragment() {
        Fragment newFragment = new FragmentForm0();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.fragment2, newFragment);
        transaction.commit();
    }

}
