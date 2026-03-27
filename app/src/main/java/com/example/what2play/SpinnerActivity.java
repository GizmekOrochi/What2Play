package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.Genre;

public class SpinnerActivity extends AppCompatActivity {
    private TextView questionTitle;
    private TextView previewText;
    private Spinner spinnerGenreMode;

    private Button buttonPrevious5;
    private Button buttonHome5;
    private Button buttonValidate5;

    private AppDatabase db;

    private String spinnerMode;

    private int selectedGenre1Id;
    private int selectedGenre2Id;

    private String selectedGenre1Name;
    private String selectedGenre2Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        buttonPrevious5 = findViewById(R.id.buttonPrevious5);
        buttonHome5 = findViewById(R.id.buttonHome5);
        buttonValidate5 = findViewById(R.id.buttonValidate5);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "what2play-db"
        ).allowMainThreadQueries().build();

        spinnerMode = getIntent().getStringExtra("spinner_mode");

        if (spinnerMode == null) {
            Toast.makeText(this, "Invalid spinner mode", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        setupSpinnerForMode();

        spinnerGenreMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSelectionFromSpinner(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        updateSelectionFromSpinner(0);

        buttonPrevious5.setOnClickListener(v -> finish());

        buttonHome5.setOnClickListener(v -> {
            Intent intent = new Intent(SpinnerActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        buttonValidate5.setOnClickListener(v -> {
            if (selectedGenre1Id <= 0 || selectedGenre2Id <= 0) {
                Toast.makeText(this, "No valid genre selection", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(SpinnerActivity.this, SliderActivity.class);
            intent.putExtra("genre1_id", selectedGenre1Id);
            intent.putExtra("genre2_id", selectedGenre2Id);
            startActivity(intent);
        });
    }

    private void updateSelectionFromSpinner(int position) {
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
            return;
        }

        if (genre1 == null || genre2 == null) {
            Toast.makeText(this, "Genres not found in database", Toast.LENGTH_SHORT).show();
            return;
        }

        selectedGenre1Id = genre1.id;
        selectedGenre2Id = genre2.id;

        selectedGenre1Name = genre1.name;
        selectedGenre2Name = genre2.name;

        previewText.setText("Selected genres: " + selectedGenre1Name + " / " + selectedGenre2Name);
    }

    private void setupSpinnerForMode() {
        String[] options;

        if (spinnerMode.equals("live")) {
            questionTitle.setText("Pick the live scene you would step into");
            options = new String[] {
                    "Smooth and expressive",
                    "Powerful and intense"
            };
        } else if (spinnerMode.equals("electro")) {
            questionTitle.setText("Pick the electronic scene you would step into");
            options = new String[] {
                    "Energetic, danceable",
                    "Chill",
                    "Retro"
            };
        } else {
            Toast.makeText(this, "Unknown spinner mode", Toast.LENGTH_SHORT).show();
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


}