package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.Artist;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SwipeActivity extends AppCompatActivity {

    private TextView titleText;
    private TextView artistNameText;
    private TextView noHint;
    private TextView yesHint;

    private ProgressBar artistProgress;
    private MaterialCardView artistCard;

    private Button buttonPrevious;
    private Button buttonHome;
    private Button buttonValidate;

    private float startX;
    private float startY;

    private int currentArtistIndex = 0;
    private long currentArtistStartTimeMs = 0L;

    private String genre1Name;
    private int genre1Weight;
    private String genre2Name;
    private int genre2Weight;

    private int genre1Id;
    private int genre2Id;

    private AppDatabase db;
    private final ArrayList<String> artists = new ArrayList<>();
    private final ArrayList<AcceptedArtist> acceptedArtists = new ArrayList<>();
    private final ArrayList<String> rejectedArtists = new ArrayList<>();

    private static final float SWIPE_THRESHOLD = 150f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        bindViews();

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "what2play-db"
        ).allowMainThreadQueries().build();

        readIncomingGenres();
        setupArtistList();

        setupButtons();
        setupSwipeDetection();

        artistProgress.setMax(artists.size());
        buttonValidate.setEnabled(false);
        buttonValidate.setAlpha(0.5f);

        showCurrentArtist();
    }

    private void bindViews() {
        titleText = findViewById(R.id.titleText);
        artistNameText = findViewById(R.id.artistNameText);
        noHint = findViewById(R.id.noHint);
        yesHint = findViewById(R.id.yesHint);

        artistProgress = findViewById(R.id.artistProgress);
        artistCard = findViewById(R.id.artistCard);

        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonHome = findViewById(R.id.buttonHome);
        buttonValidate = findViewById(R.id.buttonValidate);
    }

    private void readIncomingGenres() {
        Intent intent = getIntent();

        genre1Id = intent.getIntExtra("genre1_id", -1);
        genre2Id = intent.getIntExtra("genre2_id", -1);

        genre1Name = intent.getStringExtra("genre1_name");
        genre1Weight = intent.getIntExtra("genre1_weight", 3);

        genre2Name = intent.getStringExtra("genre2_name");
        genre2Weight = intent.getIntExtra("genre2_weight", 3);

        if (genre1Id == -1 || genre2Id == -1) {
            Toast.makeText(this, "Invalid genre IDs", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if (genre1Name == null) genre1Name = "Unknown";
        if (genre2Name == null) genre2Name = "Unknown";

        titleText.setText(genre1Name + " / " + genre2Name);
    }

    private void setupArtistList() {
        artists.clear();

        int totalWeight = genre1Weight + genre2Weight;
        if (totalWeight <= 0) totalWeight = 1;

        int countGenre1 = Math.round((6f * genre1Weight) / totalWeight);
        int countGenre2 = 6 - countGenre1;

        addArtistsFromGenreId(artists, genre1Id, countGenre1);
        addArtistsFromGenreId(artists, genre2Id, countGenre2);

        while (artists.size() > 6) {
            artists.remove(artists.size() - 1);
        }
    }

    private void addArtistsFromGenreId(ArrayList<String> target, int genreId, int count) {
        if (count <= 0) return;

        ArrayList<Artist> source =
                new ArrayList<>(db.genreArtistDao().getArtistsForGenre(genreId));

        for (Artist artist : source) {
            if (!target.contains(artist.name)) {
                target.add(artist.name);
                count--;
            }
            if (count == 0) {
                return;
            }
        }
    }

    private void setupButtons() {
        buttonPrevious.setOnClickListener(v -> goToPreviousArtist());
        buttonHome.setOnClickListener(v -> finish());
        buttonValidate.setOnClickListener(v -> validateChoices());
    }

    private void setupSwipeDetection() {
        artistCard.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    startX = event.getRawX();
                    startY = event.getRawY();
                    return true;

                case MotionEvent.ACTION_UP:
                    float endX = event.getRawX();
                    float endY = event.getRawY();

                    float deltaX = endX - startX;
                    float deltaY = endY - startY;

                    if (Math.abs(deltaX) > SWIPE_THRESHOLD &&
                            Math.abs(deltaX) > Math.abs(deltaY)) {

                        if (deltaX > 0) {
                            handleYes();
                        } else {
                            handleNo();
                        }
                    }

                    return true;
            }
            return false;
        });
    }

    private void showCurrentArtist() {
        if (currentArtistIndex < 0) {
            currentArtistIndex = 0;
        }

        if (currentArtistIndex >= artists.size()) {
            artistNameText.setText("Finished");
            artistProgress.setProgress(artists.size());

            noHint.setAlpha(0.1f);
            yesHint.setAlpha(0.1f);

            buttonValidate.setEnabled(true);
            buttonValidate.setAlpha(1.0f);
            return;
        }

        String currentArtist = artists.get(currentArtistIndex);
        artistNameText.setText(currentArtist);

        artistProgress.setProgress(currentArtistIndex + 1);

        noHint.setAlpha(0.25f);
        yesHint.setAlpha(0.25f);

        currentArtistStartTimeMs = SystemClock.elapsedRealtime();
    }

    private void handleYes() {
        if (currentArtistIndex >= artists.size()) return;

        String currentArtist = artists.get(currentArtistIndex);
        long reactionTimeMs = SystemClock.elapsedRealtime() - currentArtistStartTimeMs;

        rejectedArtists.remove(currentArtist);
        removeAcceptedArtistIfExists(currentArtist);

        acceptedArtists.add(new AcceptedArtist(currentArtist, reactionTimeMs));

        Toast.makeText(this, currentArtist + " accepted", Toast.LENGTH_SHORT).show();

        goToNextArtist();
    }

    private void handleNo() {
        if (currentArtistIndex >= artists.size()) return;

        String currentArtist = artists.get(currentArtistIndex);

        removeAcceptedArtistIfExists(currentArtist);

        if (!rejectedArtists.contains(currentArtist)) {
            rejectedArtists.add(currentArtist);
        }

        Toast.makeText(this, currentArtist + " rejected", Toast.LENGTH_SHORT).show();

        goToNextArtist();
    }

    private void goToNextArtist() {
        currentArtistIndex++;
        showCurrentArtist();
    }

    private void goToPreviousArtist() {
        if (currentArtistIndex <= 0) {
            Toast.makeText(this, "Already at the first artist", Toast.LENGTH_SHORT).show();
            return;
        }

        currentArtistIndex--;

        String artist = artists.get(currentArtistIndex);
        removeAcceptedArtistIfExists(artist);
        rejectedArtists.remove(artist);

        showCurrentArtist();

        buttonValidate.setEnabled(false);
        buttonValidate.setAlpha(0.5f);
    }

    private void removeAcceptedArtistIfExists(String artistName) {
        for (int i = 0; i < acceptedArtists.size(); i++) {
            if (acceptedArtists.get(i).name.equals(artistName)) {
                acceptedArtists.remove(i);
                return;
            }
        }
    }

    private void validateChoices() {
        if (currentArtistIndex < artists.size()) {
            Toast.makeText(this, "Finish all artists first", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> selectedArtists = new ArrayList<>();

        if (acceptedArtists.isEmpty()) {
            // Si aucun yes, on envoie le dernier artiste affiché / traité
            if (!artists.isEmpty()) {
                selectedArtists.add(artists.get(artists.size() - 1));
            }
        } else {
            Collections.sort(acceptedArtists, new Comparator<AcceptedArtist>() {
                @Override
                public int compare(AcceptedArtist a1, AcceptedArtist a2) {
                    return Long.compare(a1.reactionTimeMs, a2.reactionTimeMs);
                }
            });

            int max = Math.min(3, acceptedArtists.size());
            for (int i = 0; i < max; i++) {
                selectedArtists.add(acceptedArtists.get(i).name);
            }
        }

        // Ici on envoie la liste à l’activité suivante
        Intent intent = new Intent(SwipeActivity.this, ListenActivity.class);
        intent.putStringArrayListExtra("selected_artists", selectedArtists);

        // Si tu veux aussi transmettre les genres reçus au cas où
        intent.putExtra("genre1_name", genre1Name);
        intent.putExtra("genre1_weight", genre1Weight);
        intent.putExtra("genre2_name", genre2Name);
        intent.putExtra("genre2_weight", genre2Weight);

        startActivity(intent);
    }

    private static class AcceptedArtist {
        String name;
        long reactionTimeMs;

        AcceptedArtist(String name, long reactionTimeMs) {
            this.name = name;
            this.reactionTimeMs = reactionTimeMs;
        }
    }
}