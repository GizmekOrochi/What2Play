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

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.Artist;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class SwipeActivity extends BaseActivity {

    private TextView titleText;
    private TextView artistNameText;

    private ProgressBar artistProgress;
    private MaterialCardView artistCard;

    private Button buttonPrevious;
    private Button buttonHome;
    private Button buttonValidate;

    private float startX;

    private int currentArtistIndex = 0;
    private long currentArtistStartTimeMs = 0L;

    private String genre1Name;
    private String genre2Name;
    private int genre1Weight;
    private int genre2Weight;
    private int genre1Id;
    private int genre2Id;

    private AppDatabase db;

    private final ArrayList<String> artists = new ArrayList<>();
    private String bestArtist1Name = null;
    private String bestArtist2Name = null;
    private String bestArtist3Name = null;

    private long bestArtist1Time = Long.MAX_VALUE;
    private long bestArtist2Time = Long.MAX_VALUE;
    private long bestArtist3Time = Long.MAX_VALUE;

    private static final float SWIPE_THRESHOLD = 150f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_swipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        titleText = findViewById(R.id.titleText);
        artistNameText = findViewById(R.id.artistNameText);
        artistProgress = findViewById(R.id.artistProgress);
        artistCard = findViewById(R.id.artistCard);
        buttonPrevious = findViewById(R.id.buttonPrevious6);
        buttonHome = findViewById(R.id.buttonHome6);
        buttonValidate = findViewById(R.id.buttonValidate6);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "what2play-db"
        ).allowMainThreadQueries().build();

        Intent intent = getIntent();

        genre1Id = intent.getIntExtra("genre1_id", -1);
        genre2Id = intent.getIntExtra("genre2_id", -1);

        genre1Name = intent.getStringExtra("genre1_name");
        genre2Name = intent.getStringExtra("genre2_name");

        genre1Weight = intent.getIntExtra("genre1_weight", 3);
        genre2Weight = intent.getIntExtra("genre2_weight", 3);

        if (genre1Id == -1 || genre2Id == -1) {
            Toast.makeText(this, "Invalid genre IDs", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        if (genre1Name == null) genre1Name = "Unknown";
        if (genre2Name == null) genre2Name = "Unknown";

        titleText.setText(genre1Name + " / " + genre2Name);

        loadArtists();

        artistProgress.setMax(artists.size());
        buttonValidate.setEnabled(false);
        buttonValidate.setAlpha(0.5f);


        artistCard.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startX = event.getRawX();
                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {
                float endX = event.getRawX();

                if (endX - startX > SWIPE_THRESHOLD) {
                    handleYesSwipe();
                } else if (startX - endX > SWIPE_THRESHOLD) {
                    handleNoSwipe();
                }

                return true;
            }

            return false;
        });

        displayCurrentArtist();
    }
        private void loadArtists() {
            artists.clear();

            int totalWeight = genre1Weight + genre2Weight;
            if (totalWeight <= 0) totalWeight = 1;

            int countGenre1 = (6 * genre1Weight) / (genre1Weight + genre2Weight);
            int countGenre2 = 6 - countGenre1;

            ArrayList<Artist> artistsGenre1 = new ArrayList<>(db.genreArtistDao().getArtistsForGenre(genre1Id));
            ArrayList<Artist> artistsGenre2 = new ArrayList<>(db.genreArtistDao().getArtistsForGenre(genre2Id));

            for (Artist artist : artistsGenre1) {
                if (artists.size() < countGenre1) {
                    if (!artists.contains(artist.name)) {
                        artists.add(artist.name);
                    }
                }
            }

            int currentGenre2Count = 0;
            for (Artist artist : artistsGenre2) {
                if (currentGenre2Count < countGenre2) {
                    if (!artists.contains(artist.name)) {
                        artists.add(artist.name);
                        currentGenre2Count++;
                    }
                }
            }

            while (artists.size() > 6) {
                artists.remove(artists.size() - 1);
            }
        }

        private void displayCurrentArtist() {
            if (currentArtistIndex < 0) {
                currentArtistIndex = 0;
            }

            if (currentArtistIndex >= artists.size()) {
                artistNameText.setText("Finished");
                artistProgress.setProgress(artists.size());
                buttonValidate.setEnabled(true);
                buttonValidate.setAlpha(1f);
                return;
            }

            artistNameText.setText(artists.get(currentArtistIndex));
            artistProgress.setProgress(currentArtistIndex + 1);
            currentArtistStartTimeMs = SystemClock.elapsedRealtime();
        }

    private void addAcceptedArtist(String name, long reactionTimeMs) {
        if (reactionTimeMs < bestArtist1Time) {
            bestArtist3Name = bestArtist2Name;
            bestArtist3Time = bestArtist2Time;

            bestArtist2Name = bestArtist1Name;
            bestArtist2Time = bestArtist1Time;

            bestArtist1Name = name;
            bestArtist1Time = reactionTimeMs;
        } else if (reactionTimeMs < bestArtist2Time) {
            bestArtist3Name = bestArtist2Name;
            bestArtist3Time = bestArtist2Time;

            bestArtist2Name = name;
            bestArtist2Time = reactionTimeMs;
        } else if (reactionTimeMs < bestArtist3Time) {
            bestArtist3Name = name;
            bestArtist3Time = reactionTimeMs;
        }
    }

    private void handleYesSwipe() {
        if (currentArtistIndex >= artists.size()) return;

        String currentArtist = artists.get(currentArtistIndex);
        long reactionTimeMs = SystemClock.elapsedRealtime() - currentArtistStartTimeMs;

        addAcceptedArtist(currentArtist, reactionTimeMs);

        Toast.makeText(this, currentArtist + " accepted", Toast.LENGTH_SHORT).show();

        currentArtistIndex++;
        displayCurrentArtist();
    }

        private void handleNoSwipe() {
            if (currentArtistIndex >= artists.size()) return;

            String currentArtist = artists.get(currentArtistIndex);

            Toast.makeText(this, currentArtist + " rejected", Toast.LENGTH_SHORT).show();

            currentArtistIndex++;
            displayCurrentArtist();
        }

    private void validateChoice() {
        if (currentArtistIndex < artists.size()) {
            Toast.makeText(this, "Finish all artists first", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> selectedArtists = new ArrayList<>();

        if (bestArtist1Name == null) {
            if (!artists.isEmpty()) {
                selectedArtists.add(artists.get(artists.size() - 1));
            }
        } else {
            selectedArtists.add(bestArtist1Name);

            if (bestArtist2Name != null) {
                selectedArtists.add(bestArtist2Name);
            }

            if (bestArtist3Name != null) {
                selectedArtists.add(bestArtist3Name);
            }
        }

        Intent intent = new Intent(SwipeActivity.this, ListenActivity.class);
        intent.putStringArrayListExtra("selected_artists", selectedArtists);
        intent.putExtra("genre1_name", genre1Name);
        intent.putExtra("genre1_weight", genre1Weight);
        intent.putExtra("genre2_name", genre2Name);
        intent.putExtra("genre2_weight", genre2Weight);

        startActivity(intent);
    }
    public void clickPrevious6(View view) {
        finish();
    }

    public void clickHome6(View view) {
        Intent intent = new Intent(SwipeActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void clickValidate6(View view) {
        validateChoice();
    }
}