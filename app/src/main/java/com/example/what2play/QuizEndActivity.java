package com.example.what2play;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.Artist;
import com.example.what2play.database.entities.Track;

import java.util.List;

// Activity displayed at the end of the quiz
// Shows the selected song and allows user to play/share it
public class QuizEndActivity extends BaseActivity {

    private static final String TAG = "QuizEndActivity";

    private Track track;
    private Artist artist;
    private TextView titleView, artistView;

    private Button playButton, shareButton, menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz_end);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d(TAG, "Activity started");

        titleView = findViewById(R.id.songTitle);
        artistView = findViewById(R.id.songArtist);
        playButton = findViewById(R.id.btnPlaySpotify);
        shareButton = findViewById(R.id.btnShare);
        menuButton = findViewById(R.id.btnMenu);

        //Initialize the  database
        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "what2play-db"
        ).allowMainThreadQueries().build();

        Log.d(TAG, "Database initialized");

        //Get track ID of the song from previous activity
        int trackId = getIntent().getIntExtra("trackId", -1);

        if (trackId == -1) {
            Log.e(TAG, "Invalid trackId");
            return;
        }

        Log.d(TAG, "Received trackId");

        //Getting from the data bas the song and the artist
        track = db.trackDao().getById(trackId);
        if (track == null) {
            Log.e(TAG, "Track not found in database");
            return;
        }
        List<Artist> artists = db.trackArtistDao().getArtistsForTrack(trackId);
        if (!artists.isEmpty()) {
            artist = artists.get(0);
            Log.d(TAG, "Artist found");
        } else {
            Log.d(TAG, "No artist found for this track");
        }

        //Displaying the song and the artist
        titleView.setText(track.name);
        artistView.setText(artist != null ? artist.name : "");

    }

    public void clickMenu(View view) {
        Log.d(TAG, "Returning to MainActivity");
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    public void clickPlaySpotify(View view) {
        if (track == null) {
            Log.e(TAG, "Track is null");
            return;
        }

        String url = "https://open.spotify.com/track/" + track.link;
        Log.d(TAG, "Opening Spotify: " + url);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    public void clickShare(View view) {
        if (track == null) {
            Log.e(TAG, "Track is null");
            return;
        }

        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String lang = prefs.getString("lang", "en");
        String spotifyUrl = "https://open.spotify.com/track/" + track.link;
        String message;

        if ("fr".equals(lang)) {
            message = "J'ai trouvé une musique sympa : " + track.name + "\n" + spotifyUrl;
        } else {
            message = "Hey, I found this song: " + track.name + "\n" + spotifyUrl;
        }

        Log.d(TAG, "Sharing message: " + message);

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(sendIntent, "Share"));
    }
}