package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.Genre;

public class SliderActivity extends BaseActivity {

    private static final String TAG = "SliderActivity";

    private int genre1Id;
    private int genre2Id;

    private String genre1Name;
    private String genre2Name;

    private int weight1;
    private int weight2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slider);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.title_question);
        TextView minText = findViewById(R.id.min_text);
        TextView maxText = findViewById(R.id.max_text);
        SeekBar seekBar = findViewById(R.id.seekBar);

        findViewById(R.id.buttonPrevious4);
        findViewById(R.id.buttonHome4);
        findViewById(R.id.buttonValidate4);

        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "what2play-db"
        ).allowMainThreadQueries().build();

        Log.d(TAG, "Database initialized");

        genre1Id = getIntent().getIntExtra("genre1_id", -1);
        genre2Id = getIntent().getIntExtra("genre2_id", -1);

        Log.d(TAG, "Received genre ids.");

        Genre genre1 = db.genreDao().getById(genre1Id);
        Genre genre2 = db.genreDao().getById(genre2Id);

        if (genre1 == null || genre2 == null) {
            Toast.makeText(this, "Genres not found in database", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Genres not found in db");
            finish();
            return;
        }

        genre1Name = genre1.name;
        genre2Name = genre2.name;

        Log.d(TAG, "Genres loaded");

        minText.setText(genre1Name);
        maxText.setText(genre2Name);

        seekBar.setMax(6);
        seekBar.setProgress(3);

        updateWeightsFromSeekBar(3);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "SeekBar changed");
                updateWeightsFromSeekBar(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "SeekBar touch started");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(TAG, "SeekBar touch stopped");
            }
        });
    }

    private void updateWeightsFromSeekBar(int progress) {
        weight2 = progress;
        weight1 = 6 - progress;
        Log.d(TAG, "Weights updated");
    }

    public void clickPrevious4(View view) {
        Log.d(TAG, "previous button clicked");
        finish();
    }

    public void clickHome4(View view) {
        Log.d(TAG, "home button clicked");
        Intent intent = new Intent(SliderActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void clickValidate4(View view) {
        Log.d(TAG, "validate button clicked");

        Intent intent = new Intent(SliderActivity.this, SwipeActivity.class);
        intent.putExtra("genre1_id", genre1Id);
        intent.putExtra("genre2_id", genre2Id);
        intent.putExtra("genre1_name", genre1Name);
        intent.putExtra("genre2_name", genre2Name);
        intent.putExtra("genre1_weight", weight1);
        intent.putExtra("genre2_weight", weight2);
        startActivity(intent);
    }
}