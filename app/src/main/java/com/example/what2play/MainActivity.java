package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        startButton = findViewById(R.id.startButton);
        addButton = findViewById(R.id.addButton);
        settingsButton = findViewById(R.id.settingsButton);
    }
    public void clickStart(View view) {
        Intent intent = new Intent(MainActivity.this, GenreActivity.class);
        startActivity(intent);
    }

    public void clickAdd(View view) {
        Intent intent = new Intent(MainActivity.this, AddSongActivity.class);
        startActivity(intent);
    }

    public void clickSettings(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }
}