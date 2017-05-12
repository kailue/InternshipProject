package com.example.kailue.internshipproject;

import android.app.Application;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by KaiLue on 11-May-17.
 */

public class MyDatabase extends Application {
    private DatabaseReference database;
    public static MyDatabase singleton = null;

    public MyDatabase getInstance(){
        if (singleton == null) {
            singleton = new MyDatabase();
        }
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        database = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDatabase() {
        return database;
    }

}
