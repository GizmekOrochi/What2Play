package com.example.what2play;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SliderActivity extends AppCompatActivity {

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

        genre1Id = getIntent().getIntExtra("genre1_id", -1);
        genre2Id = getIntent().getIntExtra("genre2_id", -1);


        minText.setText(genre1Name);
        maxText.setText(genre2Name);
        titleQuestion.setText("Choose your mix between the two genres");

        seekBar.setMax(6);
        seekBar.setProgress(3);

        updateWeightsFromSeekBar(3);

        buttonPrevious4.setOnClickListener(v -> finish());

        buttonHome4.setOnClickListener(v -> {
            Intent intent2 = new Intent(SliderActivity.this, MainActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent2);
        });
    }

    private void updateWeightsFromSeekBar(int progress) {
        weight2 = progress;
        weight1 = 6 - progress;
    }
}