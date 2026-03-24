package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddArtistActivity extends AppCompatActivity {

    Button goToSongButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artist);

        goToSongButton = findViewById(R.id.goToSongButton);

        goToSongButton.setOnClickListener(v -> {
            Intent intent = new Intent(AddArtistActivity.this, AddSongActivity.class);
            startActivity(intent);
        });
    }
}