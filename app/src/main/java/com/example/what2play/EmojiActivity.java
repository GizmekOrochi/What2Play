package com.example.what2play;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class EmojiActivity extends AppCompatActivity {

    //chips interactives
    private Chip chipBoomBap, chipWestCoast, chipTrap, chipCloudRap;

    //chips d'affichage
    private Chip chipRankA1, chipRankB1, chipRankA2, chipRankB2;

    private Button buttonPrevious2, buttonHome2, buttonValidate2;

    //stockage du classement
    private final ArrayList<String> ranking = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emoji);

        chipBoomBap = findViewById(R.id.chipBoomBap);
        chipWestCoast = findViewById(R.id.chipWestCoast);
        chipTrap = findViewById(R.id.chipTrap);
        chipCloudRap = findViewById(R.id.chipCloudRap);

        chipRankA1 = findViewById(R.id.chipRankA1);
        chipRankB1 = findViewById(R.id.chipRankB1);
        chipRankA2 = findViewById(R.id.chipRankA2);
        chipRankB2 = findViewById(R.id.chipRankB2);

        buttonPrevious2 = findViewById(R.id.buttonPrevious2);
        buttonHome2 = findViewById(R.id.buttonHome2);
        buttonValidate2 = findViewById(R.id.buttonValidate2);


        chipBoomBap.setOnClickListener(v -> toggleStyle("Boom Bap"));
        chipWestCoast.setOnClickListener(v -> toggleStyle("West Coast"));
        chipTrap.setOnClickListener(v -> toggleStyle("Trap"));
        chipCloudRap.setOnClickListener(v -> toggleStyle("Cloud Rap"));


        buttonPrevious2.setOnClickListener(v -> finish());

        buttonHome2.setOnClickListener(v -> {
            // Remplace MainActivity par ton activité d'accueil si besoin
            Intent intent = new Intent(EmojiActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });

        buttonValidate2.setOnClickListener(v -> {
            if (ranking.size() < 4) {
                return;
            }

            String top1 = ranking.get(0);
            String top2 = ranking.get(1);

            // Ici tu envoies les 2 genres principaux à l'activité suivante
            // Remplace NextActivity par la vraie activité suivante
            Intent intent = new Intent(EmojiActivity.this, SliderActivity.class);
            intent.putExtra("genre1", top1);
            intent.putExtra("genre2", top2);
            startActivity(intent);
        });

        updateRankingDisplay();
        updateChoiceChipStyles();
        updateValidateButtonState();
    }



    private void setupButtons() {
    }

    private void toggleStyle(String style) {
        if (ranking.contains(style)) {
            ranking.remove(style);
        } else {
            ranking.add(style);
        }

        updateRankingDisplay();
        updateChoiceChipStyles();
        updateValidateButtonState();
    }

    private void updateRankingDisplay() {
        // On met le rang dans la grille miroir, à la même position que l'emoji correspondant
        chipRankA1.setText(getRankText("Boom Bap"));
        chipRankB1.setText(getRankText("West Coast"));
        chipRankA2.setText(getRankText("Trap"));
        chipRankB2.setText(getRankText("Cloud Rap"));
    }

    private String getRankText(String style) {
        int index = ranking.indexOf(style);
        if (index == -1) {
            return "◌";
        }
        return String.valueOf(index + 1);
    }

    private void updateChoiceChipStyles() {
        updateChipStyle(chipBoomBap, ranking.contains("Boom Bap"));
        updateChipStyle(chipWestCoast, ranking.contains("West Coast"));
        updateChipStyle(chipTrap, ranking.contains("Trap"));
        updateChipStyle(chipCloudRap, ranking.contains("Cloud Rap"));
    }

    private void updateChipStyle(Chip chip, boolean selected) {
        if (selected) {
            chip.setAlpha(1.0f);
            chip.setChipStrokeWidth(4f);
            chip.setChipStrokeColor(ColorStateList.valueOf(getColor(android.R.color.holo_blue_dark)));
        } else {
            chip.setAlpha(0.6f);
            chip.setChipStrokeWidth(0f);
        }
    }

    private void updateValidateButtonState() {
        boolean enabled = ranking.size() == 4;
        buttonValidate2.setEnabled(enabled);
        buttonValidate2.setAlpha(enabled ? 1.0f : 0.5f);
    }
}