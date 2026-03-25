package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.DatabaseInitializer;

//Loading screen that initiate the database before opening the app
public class LoadingActivity extends BaseActivity {

    private static final String TAG = "LoadingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        Log.d(TAG, "Loading screen started");

        new Thread(() -> {

            // create database
            AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "what2play-db")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build();

            Log.d(TAG, "Database created");

            // fill database with initial data
            DatabaseInitializer.initiate(db);
            Log.d(TAG, "Database populated");

            // small delay to show loading screen
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "Sleep interrupted", e);
            }

            //Main screen
            runOnUiThread(() -> {
                Log.d(TAG, "Opening MainActivity");
                startActivity(new Intent(this, MainActivity.class));
                finish();
            });

        }).start();
    }
}