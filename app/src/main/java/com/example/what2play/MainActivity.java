package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;

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

        startButton.setOnClickListener(v ->
                startActivity(new Intent(this, EmojiActivity.class)));

        addButton.setOnClickListener(v ->
                startActivity(new Intent(this, AddSongActivity.class)));

        settingsButton.setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class)));
    }
}