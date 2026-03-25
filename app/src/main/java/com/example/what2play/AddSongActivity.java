package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AddSongActivity extends BaseActivity {

    Button goToArtistButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        goToArtistButton = findViewById(R.id.goToArtistButton);

        goToArtistButton.setOnClickListener(v ->
                startActivity(new Intent(this, AddArtistActivity.class)));
    }
}