package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AddArtistActivity extends BaseActivity {

    Button goToSongButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artist);

        goToSongButton = findViewById(R.id.goToSongButton);

        goToSongButton.setOnClickListener(v ->
                startActivity(new Intent(this, AddSongActivity.class)));
    }
}