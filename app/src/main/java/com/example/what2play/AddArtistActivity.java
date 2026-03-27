package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.Artist;
import com.example.what2play.database.entities.Genre;
import com.example.what2play.database.entities.GenreArtist;

import java.util.ArrayList;
import java.util.List;

//Activity to add a new artist and link it to a genre
public class AddArtistActivity extends BaseActivity {
    private static final String TAG = "AddArtistActivity";

    private EditText artistInput;
    private Spinner spinner;

    private AppDatabase db;
    private List<Genre> genres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_artist);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button backButton = findViewById(R.id.button);
        Button goToSongButton = findViewById(R.id.goToSongButton);
        Button addButton = findViewById(R.id.button2);
        artistInput = findViewById(R.id.editTextText);
        spinner = findViewById(R.id.spinner);

        //init database
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "what2play-db").allowMainThreadQueries().build();

        Log.d(TAG, "Database initialized");

        //load genres into spinner
        loadGenres();

        //return to the main menu
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        //add artist button
        addButton.setOnClickListener(v -> addArtist());

        //go to next screen
        goToSongButton.setOnClickListener(v -> {
            Log.d(TAG, "Going to AddSongActivity");
            startActivity(new Intent(this, AddSongActivity.class));
        });
    }

    //load genres and display them in spinner
    private void loadGenres() {
        genres = db.genreDao().getAll();

        Log.d(TAG, "Genres loaded: " + genres.size());

        List<String> names = new ArrayList<>();
        for (Genre g : genres) {
            names.add(g.name);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                names
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void addArtist() {

        String artistName = artistInput.getText().toString().trim();

        if (artistName.isEmpty()) {
            Toast.makeText(this, "Enter artist name", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Empty artist name");
            return;
        }

        //get selected genre
        Genre selectedGenre = genres.get(spinner.getSelectedItemPosition());

        Log.d(TAG, "Adding artist: " + artistName + " (genre: " + selectedGenre.name + ")");

        //check if artist already exists
        List<Artist> allArtists = db.artistDao().getAll();

        for (Artist a : allArtists) {
            if (a.name.equalsIgnoreCase(artistName)) {
                Toast.makeText(this, "Artist already exists", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Artist already exists: " + artistName);
                return;
            }
        }

        //insert artist
        Artist artist = new Artist();
        artist.name = artistName;

        int artistId = (int) db.artistDao().insert(artist);

        Log.d(TAG, "Artist inserted");

        //link artist to genre
        GenreArtist rel = new GenreArtist();
        rel.artistId = artistId;
        rel.genreId = selectedGenre.id;

        db.genreArtistDao().insert(rel);

        Log.d(TAG, "Artist linked to genre id");

        Toast.makeText(this, "Artist added!", Toast.LENGTH_SHORT).show();

        artistInput.setText("");
    }
}