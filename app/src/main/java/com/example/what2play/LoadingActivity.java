package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.DatabaseInitializer;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        new Thread(() -> {

            //Create DB
            AppDatabase db = Room.databaseBuilder(
                    getApplicationContext(),
                    AppDatabase.class,
                    "what2play-db"
            ).allowMainThreadQueries().build();

            //Populate DB
            DatabaseInitializer.populate(db);

            // Small delay to show loading (optional)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //Go to MainActivity
            runOnUiThread(() -> {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            });

        }).start();
    }
}