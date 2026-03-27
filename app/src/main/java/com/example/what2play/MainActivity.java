package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.Track;

public class MainActivity extends BaseActivity {

    Button startButton, addButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        addButton = findViewById(R.id.addButton);
        settingsButton = findViewById(R.id.settingsButton);

        //testbutton
        Button testQuizButton = findViewById(R.id.testQuizButton);

        testQuizButton.setOnClickListener(v -> {

            //Find TEARS track in DB
            new Thread(() -> {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                                AppDatabase.class, "what2play-db")
                        .allowMainThreadQueries()
                        .build();

                int trackId = -1;

                for (Track t : db.trackDao().getAll()) {
                    if (t.name.equals("TEARS")) {
                        trackId = t.id;
                        break;
                    }
                }

                int finalTrackId = trackId;

                runOnUiThread(() -> {
                    Intent intent = new Intent(this, QuizEndActivity.class);
                    intent.putExtra("trackId", finalTrackId);
                    startActivity(intent);
                });

            }).start();
        });

        startButton.setOnClickListener(v ->
                startActivity(new Intent(this, GenreActivity.class)
                ));

        addButton.setOnClickListener(v ->
                startActivity(new Intent(this, AddSongActivity.class)));

        settingsButton.setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class)));

    }
}