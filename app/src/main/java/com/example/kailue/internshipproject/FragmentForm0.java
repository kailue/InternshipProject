package com.example.kailue.internshipproject;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentForm0 extends Fragment {

    private DatabaseReference database;

    private EditText station, startdate, flight;
    private Spinner spinner;



    public FragmentForm0() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_form_0, null);

        MyDatabase myDatabase = (MyDatabase) getActivity().getApplication();
//        database = myDatabase.getDatabase().child("Destinations");

        station = (EditText) root.findViewById(R.id.stationtxt);
        startdate = (EditText) root.findViewById(R.id.startdatetxt);
        flight = (EditText) root.findViewById(R.id.flighttxt);

        spinner = (Spinner) root.findViewById(R.id.spinner);
        String[] destinations = {"Singapore (SIN)", "Bangkok (BKK)", "Da Nang (DAD)", "Darwin (DRW)", "Denpasar (DPS)", "Fukuoka (FUK)", "Guiyang (KWE)",
                                "Haikou (HAK)", "Ho Chi Minh City (SGN)", "Hong Kong (HKG)", "Jakarta (CGK)", "Kuala Lumpur (KUL)", "Manila (MNL)", "Medan (KNO)"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.select_dialog_item, destinations);
        spinnerAdapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        spinner.setAdapter(spinnerAdapter);
//        spinner.setPrompt("Select your station:");
//        spinnerAdapter.notifyDataSetChanged();


        SimpleDateFormat fmt = new SimpleDateFormat("EEE, MMM d, yyyy");
        Date date = new Date();
        startdate.setText("" + fmt.format(date));


        return root;
    }
}
