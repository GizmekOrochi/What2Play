package com.example.what2play;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GenreActivity extends BaseActivity {
    private static final String TAG = "GenreActivity";

    private CardView card1, card2, card3;
    private Button buttonValidate3;

    private String selectedCategory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_genre);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);

        findViewById(R.id.buttonPrevious3);
        findViewById(R.id.buttonHome3);
        buttonValidate3 = findViewById(R.id.buttonValidate3);

        buttonValidate3.setEnabled(false);
        buttonValidate3.setAlpha(0.5f);

        card1.setOnClickListener(v -> selectCategory(card1, "urban"));
        card2.setOnClickListener(v -> selectCategory(card2, "live"));
        card3.setOnClickListener(v -> selectCategory(card3, "electro"));
    }

    private void selectCategory(CardView selectedCard, String category) {
        Log.d(TAG, "Category selected: " + category);

        card1.setCardBackgroundColor(Color.WHITE);
        card2.setCardBackgroundColor(Color.WHITE);
        card3.setCardBackgroundColor(Color.WHITE);

        selectedCard.setCardBackgroundColor(Color.LTGRAY);

        selectedCategory = category;

        buttonValidate3.setEnabled(true);
        buttonValidate3.setAlpha(1.0f);
    }

    public void clickPrevious3(View view) {
        Log.d(TAG, "Previous button clicked");
        finish();
    }

    public void clickHome3(View view) {
        Log.d(TAG, "Home button clicked");
        Intent intent = new Intent(GenreActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    public void clickValidate3(View view) {
        Log.d(TAG, "Validate button clicked");

        if (selectedCategory == null) {
            Log.e(TAG, "No category selected");
            return;
        }

        Log.d(TAG, "Navigating with category: " + selectedCategory);

        switch (selectedCategory) {
            case "urban":
                Log.d(TAG, "Opening Emojiactivity");
                startActivity(new Intent(GenreActivity.this, EmojiActivity.class));
                break;

            case "live": {
                Log.d(TAG, "Opening Spinneractivity live");
                Intent intent = new Intent(GenreActivity.this, SpinnerActivity.class);
                intent.putExtra("spinner_mode", "live");
                startActivity(intent);
                break;
            }

            case "electro": {
                Log.d(TAG, "Opening Spinneractivity electro");
                Intent intent = new Intent(GenreActivity.this, SpinnerActivity.class);
                intent.putExtra("spinner_mode", "electro");
                startActivity(intent);
                break;
            }
        }
    }
}