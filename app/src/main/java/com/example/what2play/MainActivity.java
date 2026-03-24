package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button startButton, addButton, settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.startButton);
        addButton = findViewById(R.id.addButton);
        settingsButton = findViewById(R.id.settingsButton);

        // Start Activity
        startButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);
        });

        // Add Song Activity
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddSongActivity.class);
            startActivity(intent);
        });

        // Settings Activity
        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
}