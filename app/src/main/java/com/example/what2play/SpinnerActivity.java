package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.Genre;

public class SpinnerActivity extends BaseActivity {
    private static final String TAG = "SpinnerActivity";

    private TextView questionTitle;
    private TextView previewText;
    private Spinner spinnerGenreMode;

    private AppDatabase db;

    private String spinnerMode;

    private int selectedGenre1Id;
    private int selectedGenre2Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate called");

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_spinner);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        questionTitle = findViewById(R.id.questionTitle);
        previewText = findViewById(R.id.previewText);
        spinnerGenreMode = findViewById(R.id.spinnerGenreMode);

        findViewById(R.id.buttonPrevious5);
        findViewById(R.id.buttonHome5);
        findViewById(R.id.buttonValidate5);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "what2play-db"
        ).allowMainThreadQueries().build();

        Log.d(TAG, "Database initialized");

        spinnerMode = getIntent().getStringExtra("spinner_mode");
        Log.d(TAG, "Spinner mode received: " + spinnerMode);

        if (spinnerMode == null) {
            Toast.makeText(this, getString(R.string.invalid_spinner_mode), Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Spinner mode is null");
            finish();
            return;
        }

        setupSpinnerForMode();

        spinnerGenreMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Spinner item selected: " + position);
                updateSelectionFromSpinner(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(TAG, "Nothing selected in spinner");
            }
        });

        updateSelectionFromSpinner(0);
    }

    private void updateSelectionFromSpinner(int position) {
        Log.d(TAG, "Position selected: " + position);

        Genre genre1;
        Genre genre2;

        if (spinnerMode.equals("live")) {
            if (position == 0) {
                genre1 = db.genreDao().getByName("Jazz");
                genre2 = db.genreDao().getByName("Blues");
            } else {
                genre1 = db.genreDao().getByName("Rock");
                genre2 = db.genreDao().getByName("Metal");
            }
        } else if (spinnerMode.equals("electro")) {
            if (position == 0) {
                genre1 = db.genreDao().getByName("French house");
                genre2 = db.genreDao().getByName("EDM");
            } else if (position == 1) {
                genre1 = db.genreDao().getByName("Synthwave");
                genre2 = db.genreDao().getByName("Lo-Fi");
            } else {
                genre1 = db.genreDao().getByName("French house");
                genre2 = db.genreDao().getByName("Synthwave");
            }
        } else {
            Log.e(TAG, "Unknown spiner thing selected");
            return;
        }

        if (genre1 == null || genre2 == null) {
            Toast.makeText(this, getString(R.string.genres_not_found_database), Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Genre not found in db");
            return;
        }

        selectedGenre1Id = genre1.id;
        selectedGenre2Id = genre2.id;

        Log.d(TAG, "Selected genres id: " + selectedGenre1Id + ", " + selectedGenre2Id);

        String selectedGenre1Name = genre1.name;
        String selectedGenre2Name = genre2.name;

        previewText.setText(getString(R.string.selected_genres_preview, selectedGenre1Name, selectedGenre2Name));
    }

    private void setupSpinnerForMode() {
        Log.d(TAG, "Setting up spinner mode: " + spinnerMode);

        String[] options;

        if (spinnerMode.equals("live")) {
            questionTitle.setText(R.string.spinner_question_title);
            options = new String[] {
                    getString(R.string.spinner_live_option_1),
                    getString(R.string.spinner_live_option_2)
            };
        } else if (spinnerMode.equals("electro")) {
            questionTitle.setText(R.string.spinner_question_title);
            options = new String[] {
                    getString(R.string.spinner_electro_option_1),
                    getString(R.string.spinner_electro_option_2),
                    getString(R.string.spinner_electro_option_3)
            };
        } else {
            Toast.makeText(this, getString(R.string.unknown_spinner_mode), Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Unknown spinner mode");
            finish();
            return;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                options
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenreMode.setAdapter(adapter);
    }

    public void clickPrevious5(View view) {
        Log.d(TAG, "previous button clicked");
        finish();
    }

    public void clickHome5(View view) {
        Log.d(TAG, "home button clicked");
        Intent intent = new Intent(SpinnerActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void clickValidate5(View view) {
        Log.d(TAG, "validate button clicked");

        if (selectedGenre1Id <= 0 || selectedGenre2Id <= 0) {
            Toast.makeText(this, getString(R.string.no_valid_genre_selection), Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Invalid genre");
            return;
        }

        Log.d(TAG, "Going to SliderActivity with genres: " + selectedGenre1Id + ", " + selectedGenre2Id);

        Intent intent = new Intent(SpinnerActivity.this, SliderActivity.class);
        intent.putExtra("genre1_id", selectedGenre1Id);
        intent.putExtra("genre2_id", selectedGenre2Id);
        startActivity(intent);
    }
}