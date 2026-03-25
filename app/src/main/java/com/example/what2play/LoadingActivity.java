package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;

import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.DatabaseInitializer;

public class LoadingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new Thread(() -> {

            AppDatabase db = Room.databaseBuilder(
                    getApplicationContext(),
                    AppDatabase.class,
                    "what2play-db"
            ).allowMainThreadQueries().build();

            DatabaseInitializer.populate(db);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            runOnUiThread(() -> {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            });

        }).start();
    }
}