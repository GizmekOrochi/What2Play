package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddSongActivity extends AppCompatActivity {

    Button goToArtistButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        goToArtistButton = findViewById(R.id.goToArtistButton);

        goToArtistButton.setOnClickListener(v -> {
            Intent intent = new Intent(AddSongActivity.this, AddArtistActivity.class);
            startActivity(intent);
        });
    }
}