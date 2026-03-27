package com.example.what2play;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GenreActivity extends AppCompatActivity {
    private CardView card1, card2, card3;
    private Button buttonPrevious3, buttonHome3, buttonValidate3;

    private String selectedCategory = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_genrequestion);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);

        buttonPrevious3 = findViewById(R.id.buttonPrevious3);
        buttonHome3 = findViewById(R.id.buttonHome3);
        buttonValidate3 = findViewById(R.id.buttonValidate3);

        buttonValidate3.setEnabled(false);
        buttonValidate3.setAlpha(0.5f);

        card1.setOnClickListener(v -> selectCategory(card1, "urban"));
        card2.setOnClickListener(v -> selectCategory(card2, "live"));
        card3.setOnClickListener(v -> selectCategory(card3, "electro"));

        buttonValidate3.setOnClickListener(v -> {
            if (selectedCategory == null) {
                return;
            }

            switch (selectedCategory) {
                case "urban":
                    startActivity(new Intent(GenreActivity.this, EmojiActivity.class));
                    break;

                case "live":
                    //Toast.makeText(this, "Live activity not created yet", Toast.LENGTH_SHORT).show();
                    break;

                case "electro":
                    //Toast.makeText(this, "Electro activity not created yet", Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        buttonPrevious3.setOnClickListener(v -> finish());

        buttonHome3.setOnClickListener(v -> {
            Intent intent = new Intent(GenreActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        });
    }

    private void selectCategory(CardView selectedCard, String category) {
        // Remettre toutes les cartes à l'état normal
        card1.setCardBackgroundColor(Color.WHITE);
        card2.setCardBackgroundColor(Color.WHITE);
        card3.setCardBackgroundColor(Color.WHITE);

        // Mettre en évidence la carte sélectionnée
        selectedCard.setCardBackgroundColor(Color.LTGRAY);

        // Mémoriser le choix
        selectedCategory = category;

        // Activer le bouton Validate
        buttonValidate3.setEnabled(true);
        buttonValidate3.setAlpha(1.0f);
    }
}