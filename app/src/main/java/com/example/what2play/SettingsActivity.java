package com.example.what2play;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

public class SettingsActivity extends BaseActivity {

    private String selectedLang;

    private static final String TAG = "SettingsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Log.d(TAG, "onCreate");

        ImageView flagEnglish = findViewById(R.id.flagEnglish);
        ImageView flagFrench = findViewById(R.id.flagFrench);
        Button btnBack = findViewById(R.id.btnBack);
        Button btnApply = findViewById(R.id.btnApply);

        // Load saved language
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        selectedLang = prefs.getString("lang", "en");

        Log.d(TAG, "Loaded language: " + selectedLang);

        //Apply UI manually
        if (selectedLang.equals("fr")) {
            flagFrench.setAlpha(1f);
            flagEnglish.setAlpha(0.5f);
        } else {
            flagEnglish.setAlpha(1f);
            flagFrench.setAlpha(0.5f);
        }

        // English click
        flagEnglish.setOnClickListener(v -> {
            selectedLang = "en";
            Log.d(TAG, "English selected");

            flagEnglish.setAlpha(1f);
            flagFrench.setAlpha(0.5f);
        });

        // French click
        flagFrench.setOnClickListener(v -> {
            selectedLang = "fr";
            Log.d(TAG, "French selected");

            flagFrench.setAlpha(1f);
            flagEnglish.setAlpha(0.5f);
        });

        // Apply button
        btnApply.setOnClickListener(v -> {
            Log.d(TAG, "Apply clicked with the language: " + selectedLang);

            prefs.edit().putString("lang", selectedLang).apply();

            Log.d(TAG, "Language has been saved");

            // Restart app
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        // Back button
        btnBack.setOnClickListener(v -> {
            Log.d(TAG, "Back has been pressed");
            finish();
        });
    }
}