package com.example.what2play;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.Genre;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class EmojiActivity extends AppCompatActivity {

    //chips interactives
    private Chip chipBoomBap, chipWestCoast, chipTrap, chipCloudRap;

    //chips d'affichage
    private Chip chipRankA1, chipRankB1, chipRankA2, chipRankB2;

    private Button buttonPrevious2, buttonHome2, buttonValidate2;

    //stockage du classement
    private final ArrayList<Integer> ranking = new ArrayList<>();

    private AppDatabase db;
    private ArrayList<Genre> rapGenres;


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


        AppDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "what2play-db"
        ).allowMainThreadQueries().build();

        rapGenres = new ArrayList<>(db.genreDao().getGenresForEmojiQuestion());

        if (rapGenres == null || rapGenres.size() != 4) {
            return;
        }

        chipBoomBap.setOnClickListener(v -> toggleStyle((int) chipBoomBap.getTag()));
        chipWestCoast.setOnClickListener(v -> toggleStyle((int) chipWestCoast.getTag()));
        chipTrap.setOnClickListener(v -> toggleStyle((int) chipTrap.getTag()));
        chipCloudRap.setOnClickListener(v -> toggleStyle((int) chipCloudRap.getTag()));


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

            int genre1Id = ranking.get(0);
            int genre2Id = ranking.get(1);
            /*Genre genre1 = db.genreDao().getByName(top1);
            Genre genre2 = db.genreDao().getByName(top2);


            if (genre1 == null || genre2 == null) {
                Toast.makeText(this, "Genre not found in database", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ici tu envoies les 2 genres principaux à l'activité suivante
            // Remplace NextActivity par la vraie activité suivante
            Intent intent = new Intent(EmojiActivity.this, SliderActivity.class);
            intent.putExtra("genre1_id", genre1.id);
            intent.putExtra("genre2_id", genre2.id);
            startActivity(intent);
            */
        });

        updateRankingDisplay();
        setupGenreChipsFromDb();
        updateChoiceChipStyles();
        updateValidateButtonState();
    }



    private void setupButtons() {
    }

    private void toggleStyle(int genreId) {
        if (ranking.contains(genreId)) {
            ranking.remove(Integer.valueOf(genreId));
        } else {
            ranking.add(genreId);
        }

        updateRankingDisplay();
        updateChoiceChipStyles();
        updateValidateButtonState();
    }

    private void updateRankingDisplay() {
        // On met le rang dans la grille miroir, à la même position que l'emoji correspondant
        chipRankA1.setText(getRankText((int) chipBoomBap.getTag()));
        chipRankB1.setText(getRankText((int) chipWestCoast.getTag()));
        chipRankA2.setText(getRankText((int) chipTrap.getTag()));
        chipRankB2.setText(getRankText((int) chipCloudRap.getTag()));
    }

    private void setupGenreChipsFromDb() {
        chipBoomBap.setTag(rapGenres.get(0).id);
        chipWestCoast.setTag(rapGenres.get(1).id);
        chipTrap.setTag(rapGenres.get(2).id);
        chipCloudRap.setTag(rapGenres.get(3).id);
    }

    private String getRankText(int genreId) {
        int index = ranking.indexOf(genreId);
        if (index == -1) {
            return "◌";
        }
        return String.valueOf(index + 1);
    }

    private void updateChoiceChipStyles() {
        updateChipStyle(chipBoomBap, ranking.contains((int) chipBoomBap.getTag()));
        updateChipStyle(chipWestCoast, ranking.contains((int) chipWestCoast.getTag()));
        updateChipStyle(chipTrap, ranking.contains((int) chipTrap.getTag()));
        updateChipStyle(chipCloudRap, ranking.contains((int) chipCloudRap.getTag()));;
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