package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.*;

import java.util.ArrayList;
import java.util.List;

// Activity to add a new song and link it to an artist
public class AddSongActivity extends BaseActivity {
    private static final String TAG = "AddSongActivity";

    private Spinner genreSpinner;
    private Spinner artistSpinner;
    private EditText songNameInput;
    private EditText linkInput;

    private AppDatabase db;

    private List<Genre> genres;
    private List<Artist> currentArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_song);

        // init UI
        genreSpinner = findViewById(R.id.spinner);
        artistSpinner = findViewById(R.id.spinner2);
        songNameInput = findViewById(R.id.editTextText);
        linkInput = findViewById(R.id.editTextText2);
        Button addButton = findViewById(R.id.button2);
        Button goToArtistButton = findViewById(R.id.goToArtistButton);

        //init DB
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "what2play-db").allowMainThreadQueries().build();
        Log.d(TAG, "Database initialized");

        //load genres
        loadGenres();

        //when genre changes we update artists spinner
        genreSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Genre selectedGenre = genres.get(position);
                Log.d(TAG, "Selected genre: " + selectedGenre.name);
                loadArtistsForGenre(selectedGenre.id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        //add song button
        addButton.setOnClickListener(v -> addSong());

        //Going to AddSongActivity activity
        goToArtistButton.setOnClickListener(v -> {
            Log.d(TAG, "Navigating to AddArtistActivity");
            startActivity(new Intent(this, AddArtistActivity.class));
        });
    }

    //load genres into spinner
    private void loadGenres() {
        genres = db.genreDao().getAll();
        Log.d(TAG, "Genres loaded: " + genres.size());
        List<String> names = new ArrayList<>();
        for (Genre g : genres) {
            names.add(g.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(adapter);
    }

    //load artists depending on selected genre
    private void loadArtistsForGenre(int genreId) {
        currentArtists = db.genreArtistDao().getArtistsForGenre(genreId);
        Log.d(TAG, "Artists loaded for genre " + genreId);
        List<String> names = new ArrayList<>();
        for (Artist a : currentArtists) {
            names.add(a.name);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, names);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        artistSpinner.setAdapter(adapter);
    }

    //add a new song
    private void addSong() {
        String songName = songNameInput.getText().toString().trim();
        String link = linkInput.getText().toString().trim();
        if (songName.isEmpty()) {
            Toast.makeText(this, "Enter song name", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Empty song name");
            return;
        }

        //check if song already exists
        List<Track> allTracks = db.trackDao().getAll();
        for (Track t : allTracks) {
            if (t.name.equalsIgnoreCase(songName)) {
                Toast.makeText(this, "Song already exists", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Song already exists: " + songName);
                return;
            }
        }

        //insert track
        Track t = new Track();
        t.name = songName;
        t.link = link;
        int trackId = (int) db.trackDao().insert(t);
        Log.d(TAG, "Track inserted");

        //link to artist
        if (currentArtists == null || currentArtists.isEmpty()) {
            Toast.makeText(this, "No artist available", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "No artists available");
            return;
        }

        Artist selectedArtist = currentArtists.get(artistSpinner.getSelectedItemPosition());
        TrackArtist rel = new TrackArtist();
        rel.trackId = trackId;
        rel.artistId = selectedArtist.id;
        db.trackArtistDao().insert(rel);
        Log.d(TAG, "Track linked");
        Toast.makeText(this, "Song added!", Toast.LENGTH_SHORT).show();

        //clear inputs
        songNameInput.setText("");
        linkInput.setText("");
    }
}