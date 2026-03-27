package com.example.what2play;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.what2play.database.AppDatabase;
import com.example.what2play.database.entities.Genre;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class EmojiActivity extends BaseActivity {

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
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emoji);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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


        db = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "what2play-db"
        ).allowMainThreadQueries().build();

        rapGenres = new ArrayList<>(db.genreDao().getRapGenresForEmojiQuestion());

        if (rapGenres.size() != 4) {
            finish();
            return;
        }

        chipBoomBap.setOnClickListener(v -> toggleStyle((int) chipBoomBap.getTag()));
        chipWestCoast.setOnClickListener(v -> toggleStyle((int) chipWestCoast.getTag()));
        chipTrap.setOnClickListener(v -> toggleStyle((int) chipTrap.getTag()));
        chipCloudRap.setOnClickListener(v -> toggleStyle((int) chipCloudRap.getTag()));


        setupGenreChips();
        updateRankingDisplay();
        updateValidateButtonState();
    }



    private void toggleStyle(int genreId) {
        if (ranking.contains(genreId)) {
            ranking.remove(Integer.valueOf(genreId));
        } else {
            ranking.add(genreId);
        }

        updateRankingDisplay();
        updateValidateButtonState();
    }

    private void updateRankingDisplay() {
        chipRankA1.setText(getRankText((int) chipBoomBap.getTag()));
        chipRankB1.setText(getRankText((int) chipWestCoast.getTag()));
        chipRankA2.setText(getRankText((int) chipTrap.getTag()));
        chipRankB2.setText(getRankText((int) chipCloudRap.getTag()));
    }

    private void setupGenreChips() {
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

        if (index == 0) {
            return "1️⃣";
        } else if (index == 1) {
            return "2️⃣";
        } else if (index == 2) {
            return "3️⃣";
        } else if (index == 3) {
            return "4️⃣";
        }

        return "◌";
    }

    private void updateValidateButtonState() {
        boolean enabled = ranking.size() == 4;
        buttonValidate2.setEnabled(enabled);
        buttonValidate2.setAlpha(enabled ? 1.0f : 0.5f);
    }

    public void clickPrevious2(View view) {
        finish();
    }

    public void clickHome2(View view) {
        Intent intent = new Intent(EmojiActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void clickValidate2(View view) {
        if (ranking.size() < 4) {
            return;
        }

        int genre1Id = ranking.get(0);
        int genre2Id = ranking.get(1);

        Intent intent = new Intent(EmojiActivity.this, SliderActivity.class);
        intent.putExtra("genre1_id", genre1Id);
        intent.putExtra("genre2_id", genre2Id);
        startActivity(intent);
    }

}