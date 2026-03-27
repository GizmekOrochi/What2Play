package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
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

public class SliderActivity extends BaseActivity {

    private TextView titleQuestion;
    private TextView minText;
    private TextView maxText;
    private SeekBar seekBar;
    private Button buttonPrevious4, buttonHome4, buttonValidate4;

    private int genre1Id;
    private int genre2Id;

    private String genre1Name;
    private String genre2Name;

    private int weight1;
    private int weight2;

    private AppDatabase db;

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

        titleQuestion = findViewById(R.id.title_question);
        minText = findViewById(R.id.min_text);
        maxText = findViewById(R.id.max_text);
        seekBar = findViewById(R.id.seekBar);

        buttonPrevious4 = findViewById(R.id.buttonPrevious4);
        buttonHome4 = findViewById(R.id.buttonHome4);
        buttonValidate4 = findViewById(R.id.buttonValidate4);

        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "what2play-db"
        ).allowMainThreadQueries().build();

        genre1Id = getIntent().getIntExtra("genre1_id", -1);
        genre2Id = getIntent().getIntExtra("genre2_id", -1);

        Genre genre1 = db.genreDao().getById(genre1Id);
        Genre genre2 = db.genreDao().getById(genre2Id);

        if (genre1 == null || genre2 == null) {
            Toast.makeText(this, "Genres not found in database", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        genre1Name = genre1.name;
        genre2Name = genre2.name;

        minText.setText(genre1Name);
        maxText.setText(genre2Name);

        seekBar.setMax(6);
        seekBar.setProgress(3);

        updateWeightsFromSeekBar(3);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateWeightsFromSeekBar(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    private void updateWeightsFromSeekBar(int progress) {
        weight2 = progress;
        weight1 = 6 - progress;
    }
    public void clickPrevious4(View view) {
        finish();
    }

    public void clickHome4(View view) {
        Intent intent = new Intent(SliderActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void clickValidate4(View view) {
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